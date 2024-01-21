package com.example.mockbe.service.products;

import com.example.mockbe.dto.ProductDto1;
import com.example.mockbe.dto.ResDistributor;
import com.example.mockbe.dto.ResProduct;
import com.example.mockbe.model.distributor.Distributor;
import com.example.mockbe.model.product.Image;
import com.example.mockbe.model.product.Product;
import com.example.mockbe.model.product.Statuss;

import com.example.mockbe.model.transcation.Statusss;
import com.example.mockbe.model.transcation.Transcation;

import com.example.mockbe.repository.DistributorRepository;
import com.example.mockbe.repository.ImageProductRepository;
import com.example.mockbe.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ProductsServiceImp implements ProductsService {

    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    public DistributorRepository distributorRepository;

    @Override
    public ResProduct createProduct(ProductDto1 product) {
        try {
            Product newProduct = new Product();
            newProduct.setProductId(product.getProductId());
            newProduct.setProductName(product.getProductName());
            newProduct.setDescription(product.getDescription());
            newProduct.setColor(product.getColor());
            newProduct.setSize(product.getSize());
            String quantityString = product.getQuantity();
            Integer costInteger = Integer.parseInt(quantityString);
            newProduct.setQuantity(costInteger);
            String costString = product.getCost();
            Double costDouble = Double.parseDouble(costString);
            newProduct.setCost(costDouble);
            newProduct.setDistributor(product.getDistributor_code());
            newProduct.setCreatedAt(LocalDateTime.now());
            newProduct.setUpdatedAt(LocalDateTime.now());
            newProduct.setStatus(Statuss.available);
            ResProduct resProduct = new ResProduct("0",productsRepository.save(newProduct));
            return resProduct;

        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Product> getProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        return productsRepository.findById(id).orElse(null);
    }

    @Override
    public Product getProductByProductCode(String code) {
        return productsRepository.findByProductId(code);
    }

    @Override
    public Page<Product> getPageProducts(int page) {
        return productsRepository.getPageProduct(page);
    }

    @Override
    public Page<Product> getPageCode(int page, String code) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Product> product = productsRepository.findByDistributor(code,pageable);
        return productsRepository.findByDistributor(code,pageable);
    }

    // test
    @Autowired
    ImageProductRepository imageProductRepository;
    @Override
    public List<Image> listImage() {
        return imageProductRepository.findAll();
    }

    @Override
    public Optional<Image> getOne(int id) {
        return imageProductRepository.findById(id);
    }

    @Override
    public void saveImage(Image image) {imageProductRepository.save(image);
    }

    @Override
    public List<Image> listImageByProductId(String productId) {
        return imageProductRepository.findByProductId(productId);
    }

    @Override
    public void delete(int id) {imageProductRepository.deleteById(id);
    }

    @Override
    public boolean exists(int id) {
        return imageProductRepository.existsById(id);
    }

    @Override
    public String deleteProduct(String code) {
        deleteProductImage(code);
        productsRepository.deleteByProductId(code);
        return "thành công";
    }

    @Override
    public String deleteProductImage(String code) {
        imageProductRepository.deleteByProductId(code);
        return "thành công";
    }
    @Override
    public ResProduct updateProduct(ProductDto1 product, String code) {
        try {
            Product newProduct = productsRepository.findByProductId(code);
            newProduct.setProductName(product.getProductName());
            newProduct.setDescription(product.getDescription());
            newProduct.setColor(product.getColor());
            newProduct.setSize(product.getSize());
            String quantityString = product.getQuantity();
            Integer costInteger = Integer.parseInt(quantityString);
            newProduct.setQuantity(costInteger);
            String costString = product.getCost();
            Double costDouble = Double.parseDouble(costString);
            newProduct.setCost(costDouble);
            newProduct.setDistributor(product.getDistributor_code());

            newProduct.setCreatedAt(LocalDateTime.now());
            newProduct.setUpdatedAt(LocalDateTime.now());
            String quantityStringSold = product.getQuantitySold();
            Integer soldInteger = Integer.parseInt(quantityStringSold);
            newProduct.setQuantitySold(soldInteger);
            newProduct.setStatus(Statuss.available);
            ResProduct resProduct = new ResProduct("0",productsRepository.save(newProduct));
            return resProduct;

        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public String browserProduct(long id, String status, String description) {
        Product product =  productsRepository.findById(id).orElse(null);
        product.setStatus(Statuss.valueOf(status));
        product.setUpdatedAt(LocalDateTime.now());
        productsRepository.save(product);
        return "thành công";
    }
}



