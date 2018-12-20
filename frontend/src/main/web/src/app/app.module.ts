import {NgModule} from '@angular/core';
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "./app.routing.module";
import {AppComponent} from "./app.component";
import {LoginModule} from "./login/login.module";
import {HomeModule} from "./home/home.module";
import {NavbarComponent} from "./navbar/navbar.component";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    LoginModule,
    HomeModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
