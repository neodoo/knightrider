import { Component } from '@angular/core';
import { ViewController, NavParams, NavController, App } from 'ionic-angular';
import { LoginPage } from '../login/login';
import { HomePage } from '../home/home';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { NativeGeocoder, NativeGeocoderReverseResult, NativeGeocoderForwardResult } from '@ionic-native/native-geocoder';

@Component({

    selector: 'myProfile',
    templateUrl: 'myProfile.html'

})

export class MyProfile {

    carModalElement:any;
    name:any;
    tomcatUrl:any;
    keycloakUrl:any;
    profileUrl:any;
    travelsUrl:any;
    numTravels:any;
    cost:any;
    time:any;
    average:any;
    shownGroup = null;
    travelList:{vin:String, model: String, date_start:Date, latitude_start: any, longitude_start: any,date_end: Date, latitude_end: any, longitude_end:any, cost:any,time:any}[];

    constructor(public viewCtrl: ViewController, public params: NavParams, public navCtrl: NavController, public http: Http, public nativeGeocoder: NativeGeocoder,public appCtrl:App ) {

        this.tomcatUrl = "https://192.168.1.144:8443";
        this.keycloakUrl = "http://192.168.1.144:9080/auth/realms/knightrider_realm/protocol/openid-connect/token"
        this.profileUrl = "/renting-services/api/1/customer/profile?username="
        this.travelsUrl = "/renting-services/api/1/customer/travels?username="
        this.getMyProfile();  

    }

    getMyProfile(){

        if (window.localStorage.getItem('access_token') != null){
            
            this.gettingMyProfile();

        } else {

            this.viewCtrl.dismiss();
            this.appCtrl.getRootNav().push(LoginPage);
        
        }

    }
    
    gettingMyProfile(){

        let headers = new Headers();
        headers.append('Authorization', 'bearer ' + window.localStorage.getItem('access_token'));

        this.http.get(this.tomcatUrl + this.profileUrl + window.localStorage.getItem("user"), {headers : headers}).map(res => res.json()).subscribe(data => {
                
            if(data.response.num_travels!=0){

                this.name= data.response.name.toUpperCase();
                this.numTravels=data.response.num_travels;
                this.cost=data.response.cost;
                this.time=this.timeConversion(data.response.time);
                this.average=data.response.average;

            } else{   

                this.numTravels=0;                        
                this.cost=0;
                this.time=0;
                this.average=0;
                this.name= data.response.name;
                
            }
            this.gettingMyList();
           
            }, e => {
            this.login();            
        });

    }

    gettingMyList(){

        let headers = new Headers();
        headers.append('Authorization', 'bearer ' + window.localStorage.getItem('access_token'));

        this.http.get(this.tomcatUrl + this.travelsUrl + window.localStorage.getItem("user"), {headers : headers}).map(res => res.json()).subscribe(data => {
                
            this.travelList=data.response;
            this.changeDate();
            this.getAddress(data.response);
            this.formatTime(data.response);
            
        }, e => {
                this.appCtrl.getRootNav().setRoot(HomePage);
            });

    }

    changeDate(){

        for( let travel of this.travelList){

            travel.date_start = this.dateConversion(travel.date_start);
            travel.date_end = this.dateConversion(travel.date_end);

        }
    }

    dateConversion(time){

        var date = new Date(time);
        return date;

    }

    getAddress(elementList){
        
        elementList.forEach(travel => {

        this.nativeGeocoder.reverseGeocode(travel.latitude_start,  travel.longitude_start)
                .then((result: NativeGeocoderReverseResult) =>  travel.address_start=result.street)
                .catch((error: any) => console.log(error));

                this.nativeGeocoder.reverseGeocode(travel.latitude_end,  travel.longitude_end)
                .then((result: NativeGeocoderReverseResult) =>  travel.address_end=result.street)
                .catch((error: any) => console.log(error));
        });
    }

    formatTime(elementList){

        elementList.forEach(travel => {

            travel.time_string = this.timeConversion(travel.time);

        });
    }

    timeConversion(timeFull){
    
       var date = new Date(timeFull);
       return date.toLocaleTimeString('en-US', { timeZone: 'UTC', hour12: false });

    }


    toggleGroup(group) {

        if (this.isGroupShown(group)) {
         
          this.shownGroup = null;
      
        } else {
          
          this.shownGroup = group;
     
        }

    }

    isGroupShown(group) {

      return this.shownGroup === group;

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
                this.getMyProfile();
            }, e => {

                alert('No se ha podido iniciar la sesion intentelo de nuevo');

            });

    }
   
}
