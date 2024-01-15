package com.example.mockbe.controller;

import com.example.mockbe.dto.ProductDto;
import com.example.mockbe.request.CreateProductRequest;
import com.example.mockbe.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createProduct(@RequestPart("ProductRequest") @Valid CreateProductRequest createProductRequest, @RequestPart("file") MultipartFile image){
        ProductDto productDto = productService.createProduct(createProductRequest, image);
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }
}
