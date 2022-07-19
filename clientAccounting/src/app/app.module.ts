import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {ComputerService} from "./services/computer.service";
import {authInterceptorProviders} from "./interceptor/auth.interceptor";
import { CardComponent } from './card/card.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { TimeLineComponent } from './time-line/time-line.component';
import { ServicesComponent } from './ourService/services.component';
import { CathedraGraphComponent } from './cathedra-graph/cathedra-graph.component';
import { ContactComponent } from './contact/contact.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    CardComponent,
    AdminPanelComponent,
    TimeLineComponent,
    ServicesComponent,
    CathedraGraphComponent,
    ContactComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule, FormsModule, AppRoutingModule
  ],
  providers: [ComputerService, authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
