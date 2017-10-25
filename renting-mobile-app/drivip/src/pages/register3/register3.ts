import { Component } from '@angular/core';
import { NavController, NavParams, App } from 'ionic-angular';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { FormBuilder, FormGroup, Validators, AbstractControl} from '@angular/forms';
import { HomePage } from '../home/home';
import { RegisterPage4 } from '../register4/register4';
import { ToastController } from 'ionic-angular';


@Component({
    selector: 'page-register3',
    templateUrl: 'register3.html'
})


export class RegisterPage3 {

    name:any;
    surname:any;
    email:any;
    date:any;
    phone:any;
    driveNumber:any;
    driveDate:any;
    registerForm: FormGroup;

    constructor(public navCtrl: NavController, public navParams: NavParams, public http: Http, public fb: FormBuilder, public toastCtrl: ToastController, public appCtrl:App){

        this.name=this.navParams.get('name');
        this.surname=this.navParams.get('surname');
        this.email=this.navParams.get('email');
        this.date=this.navParams.get('date');
        this.phone=this.navParams.get('phone');
        this.driveNumber=this.navParams.get('driveNumber');
        this.driveDate=this.navParams.get('driveDate');


    this.registerForm = this.fb.group({

        creditCardNumber: ['', [Validators.required, Validators.pattern("[0-9]{16}")]],
        creditCardName: ['', [Validators.required]],
        creditCardCVS : ['', [Validators.required, Validators.pattern("[0-9]{3}")]],
        creditCardDate :['', [Validators.required]],
        });

    }

    next(){

        this.navCtrl.push(RegisterPage4, {
        
            creditCardNumber: this.registerForm.get('creditCardNumber').value ,
            creditCardName: this.registerForm.get('creditCardName').value,
            creditCardCVS: this.registerForm.get('creditCardCVS').value,
            creditCardDate: this.registerForm.get('creditCardDate').value,
            driveNumber: this.driveNumber,
            driveDate: this.driveDate,
            name: this.name,
            surname: this.surname,
            email: this.email,
            phone: this.phone,
            date: this.date      
        
        });
   
    }

    previous(){
        this.navCtrl.pop();
    }

}
    
