import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "../classes/Product";
import {Order} from "../classes/Order";
import {UserService} from "./user.service";
import {resolve} from "@angular/compiler-cli";
import {OrderDialogComponent} from "../components/order-dialog/order-dialog.component";
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  newPrice!: number;
  constructor(private http: HttpClient, private userService: UserService, private matSnackBar: MatSnackBar) { }


  submitOrder(productId: string, amount: number, oldPrice: number) {
    this.newPrice = Number((Math.round((amount * oldPrice) * 100) / 100).toFixed(2));
    console.log(this.newPrice);
    const orderProduct = new Order(Date.now(), this.userService.user?.id, productId, amount, this.newPrice);
    this.http.post<Order>('http://localhost:8082/orders/produce',  orderProduct, {observe: "response"})
        .subscribe(response => {
          console.log(response.body);
          if (response.status == 200) {
            this.openSnackBar("Order sent", "Okay");
          }
        }, error => {
          this.openSnackBar("Error occured", "Okay");
          console.log(error);
        })
  }

  private openSnackBar(message: string, action: string) {
    this.matSnackBar.open(message, action, {
      duration:3000,
    })
  }

    getOrdersByCustomer(id: number | undefined) {
        return this.http.get(`http://localhost:8082/orders/getByCustomer/${id}`)
    }
}
