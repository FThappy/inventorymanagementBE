package com.example.mockbe.repository;

import com.example.mockbe.model.product.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Component
public interface ImageProductRepository extends JpaRepository<Image,Integer> {
    List<Image> findByProductId(String productId);

    void deleteByProductId(String productId);
}
