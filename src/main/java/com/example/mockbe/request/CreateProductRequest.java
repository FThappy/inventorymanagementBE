package com.example.mockbe.request;

import com.example.mockbe.model.product.Product;
import com.example.mockbe.model.product.ProductBrand;
import com.example.mockbe.model.product.ProductCategory;
import com.fasterxml.jackson.databind.JsonNode;

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
    private JsonNode images;
    private ProductCategory productCategory;
    private ProductBrand productBrand;
    private String size;
    private String color;
    private String material;
    private Integer quantity;
    private Integer quantitySold;
    private Double importPrice;
    private Double retailPrice;
    private Double wholesalePrice;
    private String distributorName;
}
