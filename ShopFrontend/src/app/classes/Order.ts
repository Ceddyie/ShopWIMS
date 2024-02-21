export class Order {
    id!: number;
    orderDate!: number;
    customerId!: number | undefined;
    productId!: string;
    amount!: number;
    totalCost!: number;

    constructor(orderDate: number, customerId: number | undefined, productId: string, amount: number, totalCost: number) {
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.productId = productId;
        this.amount = amount;
        this.totalCost = totalCost;
    }
}