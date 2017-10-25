import { Component} from '@angular/core';
import { Platform } from 'ionic-angular';
import { ViewController, NavParams, NavController, App } from 'ionic-angular';
import { LoginPage } from '../login/login';
import { HomePage } from '../home/home';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';

@Component({

    selector: 'carInfo',
    templateUrl: 'carInfo.html'

})

export class CarInfo {

    car:any;
    keycloakUrl:string;
    changeVehicleStateUrl:string;
    openDoorsUrl:string;
    reserved:Boolean;
    carModalElement:any;
    carImgContainerElement:any;
    carImgElement:any;
    carInfoContainerElement:any;

    constructor(public viewCtrl: ViewController, public params: NavParams, public navCtrl: NavController, public http: Http, public appCtrl: App) {
       
        this.car = params.get("car");
        this.changeVehicleStateUrl = "https://192.168.1.144:8443/renting-services/api/1/vehicles/";
        this.keycloakUrl = "http://192.168.1.144:9080/auth/realms/knightrider_realm/protocol/openid-connect/token"
        this.openDoorsUrl = " https://192.168.1.144:8443/car-services/api/1/vehicle/"; 

    }

    updateModal(){

        this.carModalElement = document.getElementsByClassName("car-info-modal")[0];
        this.carImgContainerElement = document.getElementsByClassName("car-img-container")[0];
        this.carImgElement = document.getElementsByClassName("car-img")[0];
        this.carInfoContainerElement = document.getElementsByClassName("car-info-container")[0];
        this.changeModal();

    }

    changeModal(){

        if (this.carModalElement.classList.contains("small") || this.carModalElement.classList.contains("initial")) {
            
            this.enlargeModal();
            
        } else {

            this.reduceModal();
            
        }

    }

    enlargeModal(){

        this.carModalElement.classList.remove("small");
        this.carModalElement.classList.remove("initial");
        this.carModalElement.classList.add("large");
        this.carImgContainerElement.classList.remove("initial");
        this.carImgContainerElement.classList.remove("small");
        this.carImgContainerElement.classList.add("large");
        this.carInfoContainerElement.classList.remove("small");
        this.carInfoContainerElement.classList.add("large");
        this.carImgElement.classList.remove("initial");
        this.carImgElement.classList.remove("small");
        this.carImgElement.classList.add("large");
   
    }

    reduceModal(){
        // Move the car
        this.carImgElement.classList.remove("large");
        this.carImgElement.classList.add("small");

        // Update modal design
        setTimeout( () => {

                this.carInfoContainerElement.classList.remove("large");
                this.carInfoContainerElement.classList.add("small");
                this.carImgContainerElement.classList.remove("large");
                this.carImgContainerElement.classList.add("small");
                this.carModalElement.classList.remove("large");
                this.carModalElement.classList.add("small");

            }, 500);
   
    }

    block(){

        if (window.localStorage.getItem('access_token') != null){
            
            if(this.car.rent_state =="unblocked"){
               this.blocking()
            } else if(this.car.rent_state == "blocked"){
                this.unblock();
            }

        } else {

            this.viewCtrl.dismiss();
            this.appCtrl.getRootNav().setRoot(LoginPage);

        }
      
    }

    blocking(){

        var headers = new Headers();
        headers.append('Authorization', 'bearer ' + window.localStorage.getItem('access_token'));
        let options = new RequestOptions({ headers: headers });
        this.http.post(this.changeVehicleStateUrl + this.car.id_vehicle + "/block?username="+ window.localStorage.getItem('user'), null, options)
            .map(response => response.json())
            .subscribe(data => { 

                if(data.response.result){

                     alert("Su vehiculo ha sido reservado");

                } else{

                    alert(data.response.reason)
                }

                    this.viewCtrl.dismiss();
                    this.appCtrl.getRootNav().setRoot(HomePage);

                }, e => {
                this.login("block")
         });  
        
    }

    unblock(){
        
        var headers = new Headers();
        headers.append('Authorization', 'bearer ' + window.localStorage.getItem('access_token'));
        let options = new RequestOptions({ headers: headers });
        var res = this.http
            .post(this.changeVehicleStateUrl + this.car.id_vehicle + "/unblock?username="+ window.localStorage.getItem('user'),null, options)
            .map(response => response.json())
            .subscribe(data => {  

                if(data.response.result){

                    alert("Su coche ha sido liberado");
                    let buttonReserved = document.getElementById("book");
                    buttonReserved.classList.remove("reserved");

                } else{

                    alert(data.response.reason)
                        
                }

                    this.viewCtrl.dismiss();
                    this.appCtrl.getRootNav().setRoot(HomePage);


            }, e => {
                this.login("unblock");
         });  

    }

    startTravel(){

        if (window.localStorage.getItem('access_token') != null){
            
            this.startTraveling();

        } else {

            this.viewCtrl.dismiss();
            this.appCtrl.getRootNav().push(LoginPage);
        
        }

    }

    startTraveling(){

        var headers = new Headers();
        headers.append('Authorization', 'bearer ' + window.localStorage.getItem('access_token'));
        let options = new RequestOptions({ headers: headers });
        
        var res = this.http
            .post(this.changeVehicleStateUrl + this.car.id_vehicle + "/travel_start?username=" + window.localStorage.getItem('user'),null, options)
            .map(response => response.json())
            .subscribe(data => {   
                if (data.response.result){
                    
                    this.openDoors();

                } else{

                    alert(data.response.reason)

            }}, e => {
                this.login("traveling");
            });
    
    }

    openDoors(){

        var headers = new Headers();
        headers.append('Authorization', 'bearer ' + window.localStorage.getItem('access_token'));
        let options = new RequestOptions({ headers: headers });
        var res = this.http
            .post(this.openDoorsUrl + this.car.id_vehicle + "/command/door_unlock",null, options)
            .map(response => response.json())
            .subscribe(data => { 

                alert("Su coche se ha abierto. Ha comenzado el alquiler");
                this.viewCtrl.dismiss();
                this.appCtrl.getRootNav().setRoot(HomePage);

            }, e => {

                alert("error, no se ha podido abrir su coche.");
                this.viewCtrl.dismiss();
                this.appCtrl.getRootNav().setRoot(HomePage);
            
            });

    }

    login(state){
           
        let body = 'grant_type=password'+'&client_id=knightrider_client'+'&username=' + window.localStorage.getItem('user') +'&password=' + window.localStorage.getItem('password');
        var headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        let options = new RequestOptions({ headers: headers });
        
        var res = this.http
            .post(this.keycloakUrl, body, options)
            .map(response => response.json())
            .subscribe(data => {   
                window.localStorage.removeItem("access_token");
                window.localStorage.setItem("access_token", data.access_token.toString());
                if(state == "block"){
                    this.block();
                }
                if (state == "traveling"){
                    this.startTravel();
                }
                if(state=="unblock"){
                    this.unblock();
                }

            }, e => {

                alert('No se ha podido iniciar la sesion intentelo de nuevo');

            });

    }

}