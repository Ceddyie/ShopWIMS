import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";
import {FormsModule} from "@angular/forms";
import {OrderService} from "../../services/order.service";
import {DecimalPipe} from "@angular/common";

@Component({
  selector: 'app-order-dialog',
  standalone: true,
  imports: [
    MatLabel,
    MatInput,
    MatFormField,
    MatButton,
    FormsModule,
    DecimalPipe
  ],
  templateUrl: './order-dialog.component.html',
  styleUrl: './order-dialog.component.css'
})
export class OrderDialogComponent {
  amount: number = 1;
  newPrice: number = this.amount * this.data.price;
  productId: any = this.data.productId;
  productName: any = this.data.productName;

  constructor(private orderService: OrderService, public dialogRef: MatDialogRef<OrderDialogComponent>, @Inject(MAT_DIALOG_DATA) public data:{productId: string, productName: string, price: number}) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submitOrder() {
    this.orderService.submitOrder(this.productId, this.amount, this.data.price);
    this.onNoClick();
  }
}
