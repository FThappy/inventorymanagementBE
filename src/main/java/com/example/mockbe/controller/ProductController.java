package com.example.mockbe.controller;

import com.example.mockbe.dto.ProductDto;
import com.example.mockbe.request.CreateProductRequest;
import com.example.mockbe.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createProduct(@RequestPart("ProductRequest") @Valid CreateProductRequest createProductRequest, @RequestPart("files") List<MultipartFile> images){
        ProductDto productDto = productService.createProduct(createProductRequest, images);
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllProducts(@RequestParam(value = "page", defaultValue = "1") int page,
                                            @RequestParam(value = "size", defaultValue = "5") int size){
        return new ResponseEntity<>(productService.getAllProducts(page, size), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProducts(
                                            @RequestParam(value = "searchText", required = false) String searchText,
                                            @RequestParam(value = "category", required = false) String category,
                                            @RequestParam(value = "unit", required = false) String unit,
                                            @RequestParam(value = "createdDate", required = false) LocalDateTime createdDate,
                                            @RequestParam(value = "page", defaultValue = "1") int page,
                                            @RequestParam(value = "size", defaultValue = "5") int size){
        return new ResponseEntity<>(productService.searchProducts(searchText, category, unit, createdDate,page,size), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }
}
