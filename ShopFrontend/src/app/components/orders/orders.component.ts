import {Component, OnInit} from '@angular/core';
import {MatTable} from "@angular/material/table";
import {OrderService} from "../../services/order.service";
import {UserService} from "../../services/user.service";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {DatePipe, NgForOf} from "@angular/common";

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [
    MatTable,
    NgForOf,

  ],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent implements OnInit {
  orders: any = [];
  status!: string;
  private datePipe!: DatePipe;
  constructor(private orderService: OrderService, private userService: UserService) {
  }

  ngOnInit() {
    if (!this.userService.loggedIn()) {
      this.userService.router.navigateByUrl("/login");
    }
    this.getOrdersByCustomer(this.userService.user?.id);
  }

  private getOrdersByCustomer(id: number | undefined) {
    this.orderService.getOrdersByCustomer(id).subscribe((response) => {
      this.orders = response;
      if (this.orders) {
        this.status = "Shipped";
      } else {
        this.status = "In progress";
      }
    }, (error) => {
      console.error("Error fetching orders: ", error);
    })
  }

  protected readonly DatePipe = DatePipe;
}
