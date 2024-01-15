package com.example.mockbe.dto;

import com.example.mockbe.model.distributor.Distributor;
import com.example.mockbe.model.product.Product;
import com.example.mockbe.model.product.ProductBrand;
import com.example.mockbe.model.product.ProductCategory;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    @NotEmpty(message = "Sku is required")
    @NotBlank(message = "Sku is not blank")
    private String sku;
    @NotEmpty(message = "Product name is required")
    private String productName;
    private Double weight;
    @NotEmpty(message = "Description is required")
    private String description;
    private JsonNode images;
    @NotEmpty(message = "Category is required")
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Distributor distributor;
}
