package com.example.mockbe.service.product;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.mockbe.dto.ProductDto;
import com.example.mockbe.exception.ResourceNotFoundException;
import com.example.mockbe.mapper.ProductMapper;
import com.example.mockbe.model.distributor.Distributor;
import com.example.mockbe.model.product.Product;
import com.example.mockbe.repository.DistributorRepository;
import com.example.mockbe.repository.ProductRepository;
import com.example.mockbe.request.CreateProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private DistributorRepository distributorRepository;
    private Cloudinary cloudinary;

    @Override
    public ProductDto createProduct(CreateProductRequest createProductRequest, MultipartFile image){
        ProductDto productDto = new ProductDto();
        productDto.setSku(createProductRequest.getSku());
        productDto.setProductName(createProductRequest.getProductName());
        productDto.setWeight(createProductRequest.getWeight());
        productDto.setDescription(createProductRequest.getDescription());
        productDto.setCategory(createProductRequest.getCategory());
        productDto.setSize(createProductRequest.getSize());
        productDto.setColor(createProductRequest.getColor());
        productDto.setMaterial(createProductRequest.getMaterial());
        productDto.setQuantity(createProductRequest.getQuantity());
        productDto.setImportPrice(createProductRequest.getImportPrice());
        productDto.setRetailPrice(createProductRequest.getRetailPrice());
        productDto.setWholesalePrice(createProductRequest.getWholesalePrice());

        String distributorName = createProductRequest.getDistributorName();
        Distributor distributor = distributorRepository.findByNameContainsIgnoreCase(distributorName);
        if(distributor == null){
            throw new ResourceNotFoundException("Distributor", "name", createProductRequest.getDistributorName());
        }
        else {
            productDto.setDistributor(distributor);
        }
        // image upload
        try {
            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            productDto.setImage(uploadResult.get("url").toString());
        } catch (IOException e) {
            throw new ResourceNotFoundException("Image", "url", image.getName());
        }
        LocalDateTime now = LocalDateTime.now();
        productDto.setCreatedAt(now);
        productDto.setUpdatedAt(now);
        Product product = productMapper.toProduct(productDto);
        Product productSaved = productRepository.save(product);
        return productMapper.toProductDto(productSaved);
    }

    @Override
    public Page<Product> getAllProducts(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page-1);
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> searchProducts(String searchText, String category, String unit, LocalDateTime createdDate, int page, int size) {
        // search by sku, product name, category, unit, created date
        Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
        if(searchText != null) {
           return productRepository.findAllBySkuOrProductNameContainsIgnoreCase(searchText, searchText, pageable);
       }
       if (category != null){
           return productRepository.findAllByCategoryContainsIgnoreCase(category, pageable);
       }
       if (unit != null){
           return productRepository.findAllByUnitContainsIgnoreCase(unit, pageable);
       }
       if (createdDate != null){
           return productRepository.findAllByCreatedAt(createdDate, pageable);
       }
       return null;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", String.valueOf(id)));
        return productMapper.toProductDto(product);
    }
}
