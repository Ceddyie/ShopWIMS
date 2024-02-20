package org.example.shopbackend.order;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
