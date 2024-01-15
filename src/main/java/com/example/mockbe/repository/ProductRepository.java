package com.example.mockbe.repository;

import com.example.mockbe.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;

public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    Page<Product> findAllBySkuOrProductNameContainsIgnoreCase(String sku, String productName, Pageable pageable);
    Page<Product> findAllByCategoryContainsIgnoreCase(String category, Pageable withPage);
    Page<Product> findAllByUnitContainsIgnoreCase(String unit, Pageable withPage);
    Page<Product> findAllByCreatedAt(LocalDateTime createdDate, Pageable withPage);

}
