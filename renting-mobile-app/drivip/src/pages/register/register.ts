import { Component } from '@angular/core';
import { NavController, NavParams, App } from 'ionic-angular';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { FormBuilder, FormGroup, Validators, AbstractControl} from '@angular/forms';
import { HomePage } from '../home/home';
import { RegisterPage2 } from '../register2/register2';
import { ToastController } from 'ionic-angular';


@Component({
    selector: 'page-register',
    templateUrl: 'register.html'
})


export class RegisterPage {


    registerForm: FormGroup;

    constructor(public navCtrl: NavController, public navParams: NavParams, public http: Http, public fb: FormBuilder, public toastCtrl: ToastController, public appCtrl:App){

    this.registerForm = this.fb.group({

            name: ['', [Validators.required]],
            surname: ['', [Validators.required]],
            email: ['', [Validators.required, Validators.email]],
            phone: ['', [Validators.required, Validators.pattern("[0-9]{9}")]],
            date: ['', [Validators.required]],

        });

    }

    next(){

    this.navCtrl.push(RegisterPage2, {
        name: this.registerForm.get('name').value ,
        surname: this.registerForm.get('surname').value,
        email: this.registerForm.get('email').value,
        phone: this.registerForm.get('phone').value,
        date: this.registerForm.get('date').value    
            });
   
    }

    cancel(){
        this.appCtrl.getRootNav().setRoot(HomePage);
    }

}
    
