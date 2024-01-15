package com.example.mockbe.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    @NotEmpty(message = "Sku is required")
    private String sku;
    @NotEmpty(message = "Product name is required")
    private String productName;
    private Double weight;
    @NotEmpty(message = "Description is required")
    private String description;
    private String image;
    @NotEmpty(message = "Category is required")
    private String category;
    @NotEmpty(message = "Unit is required")
    private String unit;
    private Integer inventory;
    private String size;
    private String color;
    private String material;
    private Integer quantity;
    private Double importPrice;
    private Double retailPrice;
    private Double wholesalePrice;
    private String distributorName;
}
