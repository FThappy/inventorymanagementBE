package com.example.mockbe.service.product;

import com.example.mockbe.dto.ProductDto;
import com.example.mockbe.request.CreateProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductService {
    ProductDto createProduct(CreateProductRequest createProductRequest, MultipartFile image);
    List<ProductDto> getAllProducts();
    List<ProductDto> searchProducts(String searchText , String category, String unit, LocalDateTime createdDate);
    ProductDto getProductById(Long id);
}
