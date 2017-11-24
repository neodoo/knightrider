import { Component, ViewChild, ElementRef} from '@angular/core';
import { NavController, ModalController, App } from 'ionic-angular';
import { Geolocation } from '@ionic-native/geolocation';
import { Http, Headers, RequestOptions } from '@angular/http';
import { CarInfo } from '../carInfo/carInfo';
import { LoginPage } from '../login/login';
import { CarRent } from '../carRent/carRent';
import { NativeGeocoder, NativeGeocoderReverseResult, NativeGeocoderForwardResult } from '@ionic-native/native-geocoder';
import 'rxjs/add/operator/map';
import { Network } from '@ionic-native/network';


@Component({
    selector: 'page-home',
    templateUrl: 'home.html'

})

export class HomePage {

    @ViewChild('map') mapElement: ElementRef;
    tomcatUrl:string;
    keycloakUrl:string; 
    unblockedUrl:string;
    blockedUrl:string;
    checkTravelingUrl:string;
    showTravelingDetailsUrl:string;
    map:any;
    contactModal:any;
    contactModalTraveling:any;
    modalOpen:boolean;
    modalOpenTraveling:boolean;
    carShown:string;
    blocked:boolean;
    carModalElement:any;
    carImgContainerElement:any;
    carImgElement:any;
    carInfoContainerElement:any;
    carList:{id_vehicle:any, state: String, rent_state:String, color: String, vin: String,sunRoof: Boolean, battery_level: any, battery_range:any, latitude:any, longitude:any,inside_temp:any, outside_temp:any, sun_roof:Boolean, model:String}[];
    marker:any;
    watchOptions : { enableHighAccuracy: true};


    constructor(public navCtrl: NavController, public geolocation: Geolocation, public modalCtrl: ModalController, public http: Http, public nativeGeocoder: NativeGeocoder, public network: Network,  public appCtrl: App) {

        this.tomcatUrl = "https://192.168.1.40:8443";
        this.keycloakUrl = "http://192.168.1.40:9080/auth/realms/knightrider_realm/protocol/openid-connect/token";
        this.unblockedUrl = "/renting-services/api/1/vehicles/state/unblocked"
        this.blockedUrl = "/renting-services/api/1/vehicles/state/blocked?username=";
        this.checkTravelingUrl = "/renting-services/api/1/vehicles/state/traveling?username="
        this.showTravelingDetailsUrl = "/renting-services/api/1/customer/traveling_details?username=";

    }

    ionViewDidLoad(){ 
        this.initMap();

        let watch = this.geolocation.watchPosition(this.watchOptions);
        watch.subscribe((data) => {
            this.updateUserPosition(data)
        });

    }

    closeModals(){

        this.closeModal();
        this.closeModalTraveling();
             
    }

    closeModal(){

        if(this.contactModal){           
            this.contactModal.dismiss();
            this.modalOpen = false;
        } 

    }

    closeModalTraveling(){

        if(this.contactModalTraveling){           
            this.contactModalTraveling.dismiss();
            this.modalOpenTraveling = false;
        }   

    }

    initMap(){

        let latLng = new google.maps.LatLng(41.6563497,-0.876566);
        
        let mapOptions ={
        
            disableDefaultUI: true, 
            center: latLng,
            zoom: 12,
            mapTypeId:google.maps.MapTypeId.ROADMAP
  
        };

        var mapMaxZoom = 18;
        this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);
        this.createMarker();
        this.checkTravel();
       
    }

    loadMap(){

        this.addUnblockedVehiclesPosition();
        this.addBlockedVehiclesPosition();
        this.modalOpen = false;
        this.contactModal = this.modalCtrl.create(CarInfo, null, {cssClass: 'car-info-modal initial'});

        this.map.addListener('click', () => {
            
            this.closeModal();
       
        });

    }

    checkTravel() {

        if( window.localStorage.getItem("user") != null) {

            this.checkTraveling();

        } else {
                this.loadMap();
        }

        return false;

    }

    checkTraveling(){

        let headers = new Headers();

        this.http.get(this.tomcatUrl + this.checkTravelingUrl +  window.localStorage.getItem("user") , {headers : headers}).map(res => res.json()).subscribe(data => {

            if(data.count == 1){       

                this.isTraveling(data);
                    

            } else{
            
                this.loadMap();
        
            }
           
        });

    }

    isTraveling(data){

        this.carList=data.response;
        this.addElementsToMap(data.response);
        this.modalOpenTraveling = false;
        this.contactModalTraveling = this.modalCtrl.create(CarRent, null,  {cssClass: 'car-rent-modal'});
        
        this.map.addListener('click', () => {
                           
            this.closeModalTraveling();

        });  

    }

    addElementsToMap(elementList){

        elementList.forEach(element => {
            this.selectImg(element);
            this.addMarker(element);
            this.nativeGeocoder.reverseGeocode(element.latitude,  element.longitude)
                .then((result: NativeGeocoderReverseResult) =>  element.adress=result.street)
                .catch((error: any) => console.log(error));
    
        });
    
    }

    selectImg(car){

        if(car.model == "X"){
            car.img="assets/img/coche-x.png";
        }

        if(car.model== "S"){
            car.img="assets/img/coche-s.png";
        }

    }

    addMarker(car){

        let marker = new google.maps.Marker({
            
            title: car.vin,
            map: this.map,
            animation: google.maps.Animation.DROP,
            position: new google.maps.LatLng(car.latitude, car.longitude),
        
        });

        if(car.rent_state == "unblocked"){
            
            this.markerUnblocked(marker, car); 
       
        }

        if(car.rent_state == "blocked"){

            this.markerBlocked(marker, car);
            
        }

        if(car.rent_state == "traveling"){

            this.markerTraveling(marker,car);
            
        }
     
    }

    markerUnblocked(marker, car){

        if(car.battery_level > 65){

            marker.setIcon('assets/icon/coche-verde.png');

        } else { marker.setIcon('assets/icon/coche-naranja.png')}

        marker.addListener('click', () => {
            
            this.presentContactModal(car, marker.getPosition());
        
        });
    
    }

    markerBlocked(marker, car){

        marker.setIcon('assets/icon/coche-seleccionado.png');

        marker.addListener('click', () => {

            this.presentContactModal(car, marker.getPosition());
            
        });

    }

    markerTraveling(marker, car){

        marker.setIcon('assets/icon/coche-seleccionado.png')
        
        marker.addListener('click', () => {
                
            this.presentContactTravelingModal(car, marker.getPosition());
        
        });
    
    }

    updateModal(){

        this.carModalElement = document.getElementsByClassName("car-info-modal")[0];
        this.carImgContainerElement = document.getElementsByClassName("car-img-container")[0];
        this.carImgElement = document.getElementsByClassName("car-img")[0];
        this.carInfoContainerElement = document.getElementsByClassName("car-info-container")[0];
                    
        if( this.carModalElement.classList.contains("small") || this.carModalElement.classList.contains("initial") ){
              
            this.enlargeModal();                
            
        } else {

            this.reduceModal();
        }

    }

    changeModal(car){

        this.contactModal.data = {car: car};
        this.contactModal.dismiss();

        this.contactModal.present().then(() => {
            
            this.modalOpen = true;

        });

        this.carShown = car.vin;

    }

    presentModal(car){

        this.contactModal.data = {car: car};
            
        this.contactModal.present().then(() => {
            
                this.modalOpen = true;
                this.carShown = car.vin;
            
        });
        
    }

    presentContactModal(car, position) {

        this.map.setCenter(position);

        if(car.vin == this.carShown && this.modalOpen) {

            this.updateModal();

        } else if (car.vin != this.carShown && this.modalOpen) {

            this.changeModal(car);
            
        } else {

            this.presentModal(car);
           
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

    presentContactTravelingModal(car, position){

        this.map.setCenter(position);
        this.closeModal();

        if(this.modalOpenTraveling){

            this.contactModalTraveling.dismiss();
            this.modalOpenTraveling = false;

        } else {  

            this.showTravelingModal(car);
            
        }

    }

    showTravelingModal(car){

        var headers = new Headers();
        headers.append('Authorization', 'bearer ' + window.localStorage.getItem('access_token'));
        var res = this.http
            .get(this.tomcatUrl + this.showTravelingDetailsUrl + window.localStorage.getItem('user') , {headers : headers})
            .map(response => response.json())
            .subscribe(data => {

                this.getTravelingCar(car, data);

                this.contactModalTraveling.present().then(() => {

                    this.modalOpenTraveling= true;

                });

            }, e => {
                    alert("error showing data");
                    this.appCtrl.getRootNav().setRoot(HomePage);
        }); 
    

    }


    getTravelingCar(car, data){

        car.battery_start=data.response.battery_start;
        let time = data.response.date_start;
        var date = new Date(time);
        car.date_start=date;
        var actualDate = new Date();
        car.time = Math.floor( (actualDate.getTime() - car.date_start.getTime()));
        car.actual_minuts = Math.floor( (actualDate.getTime() - car.date_start.getTime())/60000);
        car.price = (car.actual_minuts * 0.21).toFixed(2);
        this.contactModalTraveling.data = {car: car};

    }

    addUnblockedVehiclesPosition() {

        let headers = new Headers();

        this.http.get(this.tomcatUrl + this.unblockedUrl, {headers : headers}).map(res => res.json()).subscribe(data => {
            
            this.carList=data.response;
            this.addElementsToMap(data.response);
       
        });


    }

    addBlockedVehiclesPosition() {
        
        if( window.localStorage.getItem('user') != null){

            let headers = new Headers();

            this.http.get(this.tomcatUrl + this.blockedUrl + window.localStorage.getItem("user"),   {headers : headers}).map(res => res.json()).subscribe(data => {
                
                if(data.count == 1){       
                    this.carList=data.response;
                    this.addElementsToMap(data.response);
                }
       
            });
        }

    }

    createMarker(){
        
           this.geolocation.getCurrentPosition().then((position) => {
        
            let latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);

            this.marker = new google.maps.Marker({
           
                map: this.map,
                position: latLng,
        
            });

            this.marker.setIcon('assets/icon/userMarker.png');

           
        })

    .catch((error: any) => console.log(error));

    }

    updateUserPosition(position){
        
            let latLng = new google.maps.LatLng(position.coords.latitude , position.coords.longitude);
            this.marker.setPosition(latLng);           
    
    }

}
