import * as $ from 'jquery';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { CarInfo } from '../pages/carInfo/carInfo';
import { LoginPage } from '../pages/login/login';
import { RegisterPage } from '../pages/register/register';
import { RegisterPage2 } from '../pages/register2/register2';
import { RegisterPage3 } from '../pages/register3/register3';
import { RegisterPage4 } from '../pages/register4/register4';

import { CarRent } from '../pages/carRent/carRent';
import { MyProfile } from '../pages/myProfile/myProfile';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { Geolocation } from '@ionic-native/geolocation';
import { NativeGeocoder, NativeGeocoderReverseResult, NativeGeocoderForwardResult } from '@ionic-native/native-geocoder';
import { FormBuilder, FormGroup, Validators, AbstractControl} from '@angular/forms';
import { Network } from '@ionic-native/network';
import { ToastController } from 'ionic-angular';

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    CarInfo,
    LoginPage,
    RegisterPage,
    RegisterPage2,
    RegisterPage3,
    RegisterPage4,
    CarRent,
    MyProfile,
    
  ],
  imports: [
    BrowserModule,
    HttpModule,
    IonicModule.forRoot(MyApp),
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    CarInfo,
    LoginPage,
    RegisterPage,
    RegisterPage2,
    RegisterPage3,
    RegisterPage4,
    CarRent,
    MyProfile
      ],
  providers: [
    StatusBar,
    SplashScreen,
    Geolocation,
    NativeGeocoder,
    FormBuilder,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    Network,
    ToastController,
  ]
})
export class AppModule {}
