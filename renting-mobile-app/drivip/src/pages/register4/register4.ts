import { Component } from '@angular/core';
import { NavController, NavParams, App } from 'ionic-angular';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { FormBuilder, FormGroup, Validators, AbstractControl} from '@angular/forms';
import { HomePage } from '../home/home';
import { ToastController } from 'ionic-angular';
import * as $ from 'jquery';


@Component({
    selector: 'page-register4',
    templateUrl: 'register4.html'
})


export class RegisterPage4 {

    name:any;
    surname:any;
    email:any;
    date:any;
    phone:any;
    driveNumber:any;
    driveDate:any;
    creditCardNumber: any; 
    creditCardName: any;
    creditCardCVS : any;
    creditCardDate : any;
    registerForm: FormGroup;
    registerUrl:any;


    constructor(public navCtrl: NavController, public navParams: NavParams, public http: Http, public fb: FormBuilder, public toastCtrl: ToastController, public appCtrl:App){

        this.registerUrl ="https://192.168.1.144:8443/renting-services/api/1/customer/register?email="

        this.name=this.navParams.get('name');
        this.surname=this.navParams.get('surname');
        this.email=this.navParams.get('email');
        this.date=this.navParams.get('date');
        this.phone=this.navParams.get('phone');
        this.driveNumber=this.navParams.get('driveNumber');
        this.driveDate=this.navParams.get('driveDate');
        this.creditCardNumber=this.navParams.get('creditCardNumber');
        this.creditCardName=this.navParams.get('creditCardName');
        this.creditCardCVS=this.navParams.get('creditCardCVS');
        this.creditCardDate=this.navParams.get('creditCardDate');

        this.registerForm = this.fb.group({

            password: ['', [Validators.required, Validators.minLength(6)]],
            passwordConfirmation: ['', Validators.required],
        
        },{validator: this.matchingPasswords('password', 'passwordConfirmation')}); 

    }

    ionViewDidLoad(){

        var passwordInputOne = $('#passwordOne > input');
        var passwordInputTwo = $('#passwordTwo > input');
        var passwordEyeIconOne = $('#eyeOne');
        var passwordEyeIconTwo = $('#eyeTwo');

        passwordEyeIconOne.css("z-index", 999999);
        passwordEyeIconTwo.css("z-index", 999999);

        passwordEyeIconOne.click( () =>  {this.swapPasswordInputType(passwordInputOne);});
        passwordEyeIconTwo.click( () =>  {this.swapPasswordInputType(passwordInputTwo);});
    }

    register(){

        let headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var url = this.registerUrl +  this.email + "&password=" + this.registerForm.get('password').value + "&name=" + this.name + "&surname=" + this.surname + "&birthday=" + this.date +"&phone=" + this.phone + "&driveNumber=" + this.driveNumber + "&driveDate=" + this.driveDate + "&creditNumber=" + this.creditCardNumber + "&creditCardName=" + this.creditCardName + "&creditCardCVS=" + this.creditCardCVS + "&creditCardDate=" + this.creditCardDate;
      
        var res = this.http
            .post(url,null,{headers : headers})
            .map(response => response.json())
                .subscribe(data => {
                    this.presentToast('Usuario registrado');
                }, e => {
             
                    this.presentToast('No se ha podido registrar el usuario');
                
                });
    
    }

     matchingPasswords(passwordKey: string, confirmPasswordKey: string) {
        
        return (group: FormGroup): {[key: string]: any} => {
        
            let password = group.controls[passwordKey];
            let passwordConfirmation = group.controls[confirmPasswordKey];

            if (password.value !== passwordConfirmation.value) {
                    
                    return { mismatchedPasswords: true };
            
            }
            
        }
    
    }

    swapPasswordInputType(input: any){
        var currentInputType = input.attr('type');
        (currentInputType === 'password') ? input.attr('type', 'text') : input.attr('type', 'password');
    }

    presentToast(mssg) {
    
        let toast = this.toastCtrl.create({
            
            message: mssg,
            duration: 1000,
            position: 'middle',

        });

        toast.onDidDismiss(() => {
                this.appCtrl.getRootNav().setRoot(HomePage);
  
        });

        toast.present();
    
    }

    previous() {
        this.navCtrl.pop();
    }

}
    
