import {NgModule} from '@angular/core';
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "./app.routing.module";
import {AppComponent} from "./app.component";
import {LoginModule} from "./login/login.module";
import {HomeModule} from "./home/home.module";
import {NavbarComponent} from "./navbar/navbar.component";
import {HotelsComponent} from "./hotels/hotels.component";
import {UserModule} from "./user/user.module";
import {HotelsModule} from "./hotels/hotels.module";
import {SystemAdminComponent} from "./system_admin/system.admin.component";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SystemAdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LoginModule,
    HomeModule,

    UserModule,
    HotelsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
