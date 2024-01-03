package com.example.mockbe.model.product;

import com.example.mockbe.model.supplier.Supplier;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "product_variants")
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String sizeColorCode;
    private String color;
    private String size;
    private Integer quantity;
    private String description;
    private Integer importPrice;
    private Integer retailPrice;
    private Integer wholesalePrices;
    private Integer quantityInStock;
    private Integer quantitySold;
    private String image;
    private Date createdAt;
    private Date updateAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private Status status;

}
