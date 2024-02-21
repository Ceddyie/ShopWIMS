import { Routes } from '@angular/router';
import {HomepageComponent} from "./components/homepage/homepage.component";
import {LoginComponent} from "./components/login/login.component";
import {OrdersComponent} from "./components/orders/orders.component";

export const routes: Routes = [
  { path: '', pathMatch: "full", redirectTo: "/home" },
  { path: 'home', component: HomepageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'orders', component: OrdersComponent }
];
