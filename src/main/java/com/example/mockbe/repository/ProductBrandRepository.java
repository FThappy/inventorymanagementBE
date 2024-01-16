package com.example.mockbe.repository;

import com.example.mockbe.model.product.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBrandRepository extends JpaRepository<ProductBrand, Long> {
    ProductBrand findByNameContainsIgnoreCase(String name);
}
