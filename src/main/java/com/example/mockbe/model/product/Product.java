package com.example.mockbe.model.product;

import com.example.mockbe.model.distributor.Distributor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String sku;
    @Column(nullable = false)
    private String productName;
    private Double weight;
    @Column(nullable = false)
    private String description;
    private String image;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String unit;
    private Integer inventory;
    private String size;
    private String color;
    private String material;
    private Integer quantity;
    private Double importPrice;
    private Double retailPrice;
    private Double wholesalePrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "distributor_id", referencedColumnName = "id")
    private Distributor distributor;

}
