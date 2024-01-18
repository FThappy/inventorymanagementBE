package com.example.mockbe.repository;

import com.example.mockbe.model.distributor.Distributor;
import com.example.mockbe.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    Product findByProductId( String productId );

    default Page<Product> getPageProduct(int page){
        Pageable pageable = PageRequest.of(page, 5);
        return findAll(pageable);
    }
    Page<Product> findByDistributor(String supplierCode, Pageable pageable);

    void deleteByProductId( String productId );
}
