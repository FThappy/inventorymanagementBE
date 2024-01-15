package com.example.mockbe.service.product;

import com.example.mockbe.dto.ProductDto;
import com.example.mockbe.model.product.Product;
import com.example.mockbe.request.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductService {
    ProductDto createProduct(CreateProductRequest createProductRequest, MultipartFile image);
    Page<Product> getAllProducts(int page, int size);
    Page<Product> searchProducts(String searchText , String category, String unit, LocalDateTime createdDate, int page, int size);

    ProductDto getProductById(Long id);
}
