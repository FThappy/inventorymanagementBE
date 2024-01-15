package com.example.mockbe.model.product;

import com.example.mockbe.model.distributor.Distributor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;

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
    private JsonNode images;
    private String size;
    private String color;
    private String material;
    private Integer quantity;
    private Integer quantitySold;
    private Double importPrice;
    private Double retailPrice;
    private Double wholesalePrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "distributor_id", referencedColumnName = "id")
    private Distributor distributor;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id", referencedColumnName = "id")
    private ProductCategory productCategory;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_brand_id", referencedColumnName = "id")
    private ProductBrand productBrand;
}
