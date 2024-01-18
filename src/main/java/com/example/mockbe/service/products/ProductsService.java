package com.example.mockbe.service.products;

import com.example.mockbe.dto.ProductDto1;
import com.example.mockbe.dto.ResDistributor;
import com.example.mockbe.dto.ResProduct;
import com.example.mockbe.model.distributor.Distributor;
import com.example.mockbe.model.product.Image;
import com.example.mockbe.model.product.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductsService {

    public ResProduct createProduct(ProductDto1 product);

    public ResProduct updateProduct(ProductDto1 product, String code);

//
//    public ResDistributor updateDistributor(Distributor distributor, int id);

    public List<Product> getProducts();

    public Product getProductById(long id);

    public Product getProductByProductCode(String code);

    public Page<Product> getPageProducts(int page);

    public Page<Product> getPageCode (int page, String code);

//
//    public Page<Distributor> getPageDistributors(int page);
//
    public String deleteProduct(String code);

    public String deleteProductImage(String code);
//
//    public ResDistributor updateDistributorStatus(Distributor distributor, int id);

    public List<Image> listImage();

    public List<Image> listImageByProductId(String productId);


    public Optional<Image> getOne(int id);

    public void saveImage (Image image);

    public void delete(int id);

    public boolean exists(int id);



}
