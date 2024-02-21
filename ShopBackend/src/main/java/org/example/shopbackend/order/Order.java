package org.example.shopbackend.order;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long orderDate;

    @Column
    private Long customerId;

    @Column
    private String productId;

    @Column
    private double totalCost;

    @Column
    private String address;

    @Column
    private boolean shipped;

    public Order() {}

    public Order(Long id, Long customerId, String productId, double totalCost, String address, boolean shipped) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.totalCost = totalCost;
        this.address = address;
        this.shipped = shipped;
    }

    public Order(Long customerId, String productId, double totalCost, String address, boolean shipped) {
        this.customerId = customerId;
        this.productId = productId;
        this.totalCost = totalCost;
        this.address = address;
        this.shipped = shipped;
    }
}
