import { Component } from '@angular/core';
import { NavController, NavParams, App } from 'ionic-angular';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { FormBuilder, FormGroup, Validators, AbstractControl} from '@angular/forms';
import { ToastController } from 'ionic-angular';
import { HomePage } from '../home/home';
import { RegisterPage } from '../register/register';
import * as $ from 'jquery';

@Component({
    selector: 'page-login',
    templateUrl: 'login.html'
})


export class LoginPage {
    
    keycloakUrl:string;
    username: AbstractControl;
    password: AbstractControl;
    loginForm: FormGroup;

    constructor(public navCtrl: NavController, public navParams: NavParams, public http: Http, public fb: FormBuilder, public toastCtrl: ToastController, public appCtrl:App){

        this.loginForm = fb.group({

            'username': ['', Validators.compose([Validators.required])],
            'password': ['', Validators.compose([Validators.required])]
        
        });
 
        this.username = this.loginForm.controls['username'];
        this.password = this.loginForm.controls['password'];

        this.keycloakUrl = "http://192.168.1.144:9080/auth/realms/knightrider_realm/protocol/openid-connect/token";
    
    }

    ionViewDidLoad(){

        var passwordInput = $('#password > input');
        var passwordEyeIcon = $('#eyeId');

        passwordEyeIcon.css("z-index", 999999);
        passwordEyeIcon.click( () =>  {this.swapPasswordInputType(passwordInput);});

    }

    login() {
        
        window.localStorage.setItem("user", this.loginForm.get('username').value);

        let body = 'grant_type=password'+'&client_id=knightrider_client'+'&username=' + this.loginForm.get('username').value +'&password=' + this.loginForm.get('password').value;
        var headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        let options = new RequestOptions({ headers: headers });

        var res = this.http
            .post(this.keycloakUrl, body, options)
            .map(response => response.json())
        
                .subscribe(data => {                    
                    window.localStorage.removeItem("access_token");
                    window.localStorage.setItem("access_token", data.access_token.toString());
                    window.localStorage.setItem("user",this.loginForm.get('username').value);
                    window.localStorage.setItem("password",this.loginForm.get('password').value);
                    this.presentToast('Login correcto');
                }, e => {
                    this.presentToast('No se ha podido iniciar la sesion intentelo de nuevo');

                });
    }

    presentToast(mssg) {
    
        let toast = this.toastCtrl.create({
            message: mssg,
            duration: 1000,
            position: 'middle'
        });

        toast.onDidDismiss(() => {
            this.appCtrl.getRootNav().setRoot(HomePage);
        });

        toast.present();
    }

    createAccount(){
        this.navCtrl.push(RegisterPage);
    }

    swapPasswordInputType(input: any){
        var currentInputType = input.attr('type');
        (currentInputType === 'password') ? input.attr('type', 'text') : input.attr('type', 'password');
    }

}
