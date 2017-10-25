import { Component } from '@angular/core';
import { ViewController, NavParams, NavController, App } from 'ionic-angular';
import { LoginPage } from '../login/login';
import { HomePage } from '../home/home';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';

@Component({

    selector: 'carRent',
    templateUrl: 'carRent.html'

})

export class CarRent {

    car:any;
    keycloakUrl:string;
    carModalElement:any;
    finishTravelUrl:string;
    closeDoorsUrl:string;
    
    constructor(public viewCtrl: ViewController, public params: NavParams, public navCtrl: NavController, public http: Http, public appCtrl: App) {

        this.car = params.get("car");
        this.finishTravelUrl = "https://192.168.1.144:8443/renting-services/api/1/vehicles/";
        this.keycloakUrl = "http://192.168.1.144:9080/auth/realms/knightrider_realm/protocol/openid-connect/token"
        this.closeDoorsUrl = " https://192.168.1.144:8443/car-services/api/1/vehicle/";
        this.car.actual_time=this.formatTime(this.car.time);

    }

    closeModal(){

          this.viewCtrl.dismiss();

    }

    formatTime(time){
        var date = new Date(time);
        return date.toLocaleTimeString(navigator.language, { timeZone: 'UTC', hour12: false });
    }

    finishTravel(){

        var headers = new Headers();
        headers.append('Authorization', 'bearer ' + window.localStorage.getItem('access_token'));
        let options = new RequestOptions({ headers: headers });
        var res = this.http
            .post(this.finishTravelUrl + this.car.id_vehicle + "/travel_finish?username="+ window.localStorage.getItem('user') ,null, options)
            .map(response => response.json())
            .subscribe(data => {   
                
                if(data.response.result){
                    
                    this.closeDoors(data.response.reason)
                
                } else{

                    alert(data.response.reason + ". ERROR")
                    this.appCtrl.getRootNav().setRoot(HomePage);
                
                }
            }, e => {
                this.login();
            }); 

    }


    closeDoors(cost){

        var headers = new Headers();
        headers.append('Authorization', 'bearer ' + window.localStorage.getItem('access_token'));
        let options = new RequestOptions({ headers: headers });
        var res = this.http
            .post(this.closeDoorsUrl + this.car.id_vehicle + "/command/door_lock",null, options)
            .map(response => response.json())
            .subscribe(data => {

                alert("Coche cerrado correctamente\n\nSu alquiler ha finalizado.\nEl precio total asciende a: " + cost + "€\nEsperamos que haya disfrutado de su viaje");
                this.closeModal();
                this.appCtrl.getRootNav().setRoot(HomePage);

                }, e => {

                    alert("error, no se ha podido cerrar su coche.");
                    this.closeModal();
                    this.appCtrl.getRootNav().setRoot(HomePage);
                    
            });
    }

    login(){
           
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
                this.finishTravel();

            }, e => {

                alert('No se ha podido iniciar la sesion intentelo de nuevo');

            });

    }

}

