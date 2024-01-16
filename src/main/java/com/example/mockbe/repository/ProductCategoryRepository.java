package com.example.mockbe.repository;

import com.example.mockbe.model.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    ProductCategory findByNameContainsIgnoreCase(String name);
}
