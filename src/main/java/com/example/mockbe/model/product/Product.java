package com.example.mockbe.model.product;

import com.example.mockbe.model.distributor.Distributor;
import com.example.mockbe.model.distributor.Status;
import com.example.mockbe.model.transcation.DetailTranscation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id",nullable = false, unique = true)
    private String productId;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String description;
    @Column (name = "status", columnDefinition = "VARCHAR(255) DEFAULT 'available'")
    @Enumerated(EnumType.STRING)
    private Statuss status;
    @OneToMany(targetEntity = Image.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="product_id",referencedColumnName = "product_id")
    private List<Image> images;
    @OneToMany(targetEntity = DetailTranscation.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="product_id",referencedColumnName = "product_id")
    private List<DetailTranscation> detail;
    private String size;
    private String color;
    private Integer quantity;
    private Integer quantitySold;
    private Double cost;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String distributor;
}
