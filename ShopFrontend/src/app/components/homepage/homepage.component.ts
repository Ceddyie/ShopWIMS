import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {MatDialog} from "@angular/material/dialog";
import {MatButton} from "@angular/material/button";
import {CommonModule, NgIf} from "@angular/common";
import {
    MatCard,
    MatCardActions,
    MatCardHeader,
    MatCardImage,
    MatCardSubtitle,
    MatCardTitle
} from "@angular/material/card";
import {ProductService} from "../../services/product.service";
import {OrderDialogComponent} from "../order-dialog/order-dialog.component";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  standalone: true,
    imports: [
        MatButton,
        NgIf,
        CommonModule,
        MatCard,
        MatCardHeader,
        MatCardImage,
        MatCardTitle,
        MatCardActions,
        MatCardSubtitle
    ],
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements  OnInit {
    products: any = [];
  constructor(public userService: UserService, public dialog: MatDialog, private productService: ProductService, private matDialog: MatDialog) {
  }

  ngOnInit() {
      this.getAllProducts();
    if (!this.userService.loggedIn()) {
      this.userService.router.navigateByUrl("/login");
    }
  }

  public logout() {
    this.userService.user = null;
    this.userService.router.navigateByUrl("/login");
  }

    private getAllProducts() {
        this.productService.getAllProducts().subscribe((response) => {
            this.products = response;
            console.log(this.products)
        }, (error) => {
            console.error('Error fetching products', error)
        })
    }

    openOrder(productId: string, productName: string, price: number) {
        this.openDialog(productId, productName, price);
    }

    private openDialog(productId: string, productName: string, price: number) {
        this.matDialog.open(OrderDialogComponent, {
            data: {
                productId: productId,
                productName: productName,
                price: price
            },
        })
    }
}
