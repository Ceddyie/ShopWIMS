package org.example.shopbackend.products;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String productId;

    @Column
    private String productName;

    @Column
    private String imageUrl;

    @Column
    private double price;

    @Column
    private boolean isAvailable;
}
