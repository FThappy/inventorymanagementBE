package com.example.mockbe.controller;

import com.example.mockbe.dto.*;
import com.example.mockbe.model.distributor.Distributor;
import com.example.mockbe.model.product.Image;
import com.example.mockbe.model.product.Product;

import com.example.mockbe.model.transcation.Transcation;

import com.example.mockbe.service.CloudinaryService;
import com.example.mockbe.service.distributor.DistributorService;
import com.example.mockbe.service.products.ProductsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
@RequestMapping("/api/products")
public class ProductsController {
    @Autowired
    ProductsServiceImp productsServiceImp;
    @Autowired
    CloudinaryService cloudinaryService;
    @Autowired
    public DistributorService distributorService;


    @PostMapping("/")
    // tạo sản phẩm
    public ResponseEntity<?> addProduct(@ModelAttribute ProductDto product){

        Product existingProduct = productsServiceImp.getProductByProductCode(product.getProductId());

        if(existingProduct != null){
            Responese res = new Responese("Đã tồn tại sản phẩm với mã" + product.getProductId(), "2");
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
        Distributor existingDistributor =  distributorService.getDistributorByCode(product.getDistributor_code());

        if(existingDistributor == null){
            Responese res = new Responese("Không tồn tại nhà cung cấp" + product.getDistributor_code(), "3");
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
        try{
            List<MultipartFile> multipartFiles = product.getImageFiles();

            for (MultipartFile multipartFile : multipartFiles) {
                BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
                if (bi == null) {
                    return new ResponseEntity<>("Image non valide!", HttpStatus.BAD_REQUEST);
                }

                Map result = cloudinaryService.upload(multipartFile, product.getProductId());
                Image image = new Image();
                image.setUrl((String) result.get("url"));
                image.setProductId(product.getProductId());
                productsServiceImp.saveImage(image);
            }
        }
        catch(Exception e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error post category: " + e.getMessage());
        }
        try {
            ProductDto1 dataSend = new ProductDto1();
            dataSend.setProductId(product.getProductId());
            dataSend.setProductName(product.getProductName());
            dataSend.setDescription(product.getDescription());
            dataSend.setSize(product.getSize());
            dataSend.setCost(product.getCost());
            dataSend.setColor(product.getColor());
            dataSend.setDistributor_code(product.getDistributor_code());
            dataSend.setQuantity(product.getQuantity());
            ResProduct newProduct = productsServiceImp.createProduct(dataSend);
            List<Image> img = productsServiceImp.listImageByProductId(product.getProductId());
            Product dataProduct = productsServiceImp.getProductByProductCode(product.getProductId());
            dataProduct.setImages(img);
            return ResponseEntity.status(HttpStatus.OK).body(dataProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error post category: " + e.getMessage());
        }



    }
    @GetMapping("/page")
    public ResponseEntity<?> getPageProduct(@RequestParam(value = "page", defaultValue = "0") String page){
        int pageNumber = Integer.parseInt(page);
        try {
            Page<Product> products=productsServiceImp.getPageProducts(pageNumber);
            return ResponseEntity.status(HttpStatus.OK).body(products.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }
    }
    @GetMapping("/totalpage")
    public ResponseEntity<?> getTotalPageProduct(@RequestParam(value = "page", defaultValue = "0") String page){
        int pageNumber = Integer.parseInt(page);
        try {
            Page<Product> products=productsServiceImp.getPageProducts(pageNumber);
            return ResponseEntity.status(HttpStatus.OK).body(products.getTotalPages());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }
    }
    @GetMapping("/page_code")
    public ResponseEntity<?> getPageProductCode(@RequestParam(value = "page", defaultValue = "0") String page,@RequestParam(value = "code") String code){
        int pageNumber = Integer.parseInt(page);
        try {
            Page<Product> products=productsServiceImp.getPageCode(pageNumber,code);
            return ResponseEntity.status(HttpStatus.OK).body(products.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }
    }
    @GetMapping("/totalpage_code")
    public ResponseEntity<?> getTotalPageProductCode(@RequestParam(value = "page", defaultValue = "0") String page,@RequestParam(value = "code") String code){
        int pageNumber = Integer.parseInt(page);
        try {
            Page<Product> products=productsServiceImp.getPageCode(pageNumber,code);
            return ResponseEntity.status(HttpStatus.OK).body(products.getTotalPages());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> getProducts(){
        try {
            List<Product> products=productsServiceImp.getProducts();
            return ResponseEntity.status(HttpStatus.OK).body(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    // lấy sản phẩm theo id
    public ResponseEntity<?> getProductByid(@PathVariable("id") String id){
        int idNumber = Integer.parseInt(id);
        try {
            Product product=productsServiceImp.getProductById(idNumber);
            if(product == null){
                Responese res = new Responese("Không tồn tại sản phẩm này", "4");
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }

    }
    @GetMapping("/code/{code}")
    // lấy sản phẩm theo id
    public ResponseEntity<?> getProductByCode(@PathVariable("code") String code){
        try {
            Product product=productsServiceImp.getProductByProductCode(code);
            if(product == null){
                Responese res = new Responese("Không tồn tại sản phẩm này", "4");
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }

    }
    @PutMapping("/{code}")
    public ResponseEntity<?> updateDistibutor (@PathVariable("code") String code,@ModelAttribute ProductDto product){
        Product existingProduct = productsServiceImp.getProductByProductCode( code);

        if(existingProduct == null){
            Responese res = new Responese("Không tồn tại sản phẩm với mã" +  code, "2");
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
        Distributor existingDistributor =  distributorService.getDistributorByCode(product.getDistributor_code());

        if(existingDistributor == null){
            Responese res = new Responese("Không tồn tại nhà cung cấp" + product.getDistributor_code(), "3");
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
        if(product.getImageFiles() !=null) {
            try {
                List<MultipartFile> multipartFiles = product.getImageFiles();

                if (multipartFiles != null && !multipartFiles.isEmpty()) {
                    productsServiceImp. deleteProductImage(code);
                    for (MultipartFile multipartFile : multipartFiles) {
                        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
                        if (bi == null) {
                            return new ResponseEntity<>("Image non valide!", HttpStatus.BAD_REQUEST);
                        }

                        Map result = cloudinaryService.upload(multipartFile, code);
                        Image image = new Image();
                        image.setUrl((String) result.get("url"));
                        image.setProductId(code);
                        productsServiceImp.saveImage(image);
                    }
                } else {
                    return new ResponseEntity<>("No images found!", HttpStatus.BAD_REQUEST);
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error post category: " + e.getMessage());
            }
        }
        try {
            ProductDto1 dataSend = new ProductDto1();
            dataSend.setProductName(product.getProductName());
            dataSend.setDescription(product.getDescription());
            dataSend.setSize(product.getSize());
            dataSend.setCost(product.getCost());
            dataSend.setColor(product.getColor());
            dataSend.setDistributor_code(product.getDistributor_code());
            dataSend.setQuantity(product.getQuantity());
            dataSend.setQuantitySold(product.getQuantitySold());
            ResProduct newProduct = productsServiceImp.updateProduct(dataSend, code);
            List<Image> img = productsServiceImp.listImageByProductId(code);
            Product dataProduct = productsServiceImp.getProductByProductCode(code);
            dataProduct.setImages(img);
            return ResponseEntity.status(HttpStatus.OK).body(dataProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error post category: " + e.getMessage());
        }

    }
    @DeleteMapping("/{code}")
    public ResponseEntity deleteProduct(@PathVariable("code") String code){
        Product existingProduct = productsServiceImp.getProductByProductCode( code);

        if(existingProduct == null){
            Responese res = new Responese("Không tồn tại sản phẩm với mã" +  code, "2");
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
        try{
           productsServiceImp.deleteProduct(code);
            Responese res = new Responese("Xóa sản phẩm thành công", "0");
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        catch (Exception e){
            Responese res = new Responese("Error update distributors", "1");
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PutMapping("/status")
    public ResponseEntity<?> putStatus(@RequestBody statusDto statusDto){
        Product product=productsServiceImp.getProductById(statusDto.getId());
        if(product == null){
            Responese res = new Responese("Không tồn tại sản phẩm này", "4");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        try {
            productsServiceImp.browserProduct(statusDto.getId(),statusDto.getStatus(),statusDto.getDescription());
            return ResponseEntity.status(HttpStatus.OK).body("Thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }
    }

}
