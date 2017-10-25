import { Component } from '@angular/core';
import { NavController, NavParams, App } from 'ionic-angular';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { FormBuilder, FormGroup, Validators, AbstractControl} from '@angular/forms';
import { HomePage } from '../home/home';
import { RegisterPage3 } from '../register3/register3';
import { ToastController } from 'ionic-angular';


@Component({
    selector: 'page-register2',
    templateUrl: 'register2.html'
})


export class RegisterPage2 {

    name:any;
    surname:any;
    email:any;
    date:any;
    phone:any;
    registerForm: FormGroup;



    constructor(public navCtrl: NavController, public navParams: NavParams, public http: Http, public fb: FormBuilder, public toastCtrl: ToastController, public appCtrl:App){

        this.name=this.navParams.get('name');
        this.surname=this.navParams.get('surname');
        this.email=this.navParams.get('email');
        this.date=this.navParams.get('date');
        this.phone=this.navParams.get('phone');

    this.registerForm = this.fb.group({

        driveNumber: ['', [Validators.required, Validators.pattern("[0-9]{8}[a-zA-Z]")]],
        driveDate: ['', [Validators.required]],
        });

    }

    next(){

        this.navCtrl.push(RegisterPage3, {
            driveNumber: this.registerForm.get('driveNumber').value ,
            driveDate: this.registerForm.get('driveDate').value,
            name: this.name ,
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
    
