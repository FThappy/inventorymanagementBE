package com.example.mockbe.repository;

import com.example.mockbe.model.product.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByProductId(Long id);
}
