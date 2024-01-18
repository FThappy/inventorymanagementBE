package com.example.mockbe.controller;

import com.example.mockbe.dto.*;
import com.example.mockbe.model.distributor.Distributor;
import com.example.mockbe.model.product.Product;
import com.example.mockbe.model.transcation.DetailTranscation;
import com.example.mockbe.model.transcation.Transcation;
import com.example.mockbe.service.products.ProductsServiceImp;
import com.example.mockbe.service.transcation.TranscationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
@RequestMapping("/api/transcation")
public class TranscationController {

    @Autowired
    TranscationServiceImp transcationServiceImp;
    @Autowired
    ProductsServiceImp productsServiceImp;
    // tạo đơn hàng
    @PostMapping("/")
    public ResponseEntity<?> addTranscation(@RequestBody TranscationDto transcation){
        try {
            String newTrans = transcationServiceImp.createTranscation(transcation);
            return ResponseEntity.status(HttpStatus.OK).body("Thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error post category: " + e.getMessage());
        }
    }
    @GetMapping("/page")
    public ResponseEntity<?> getPageTranscation(@RequestParam(value = "page", defaultValue = "0") String page){
        int pageNumber = Integer.parseInt(page);
        try {
            List<ResTransc> resTransc = new ArrayList<>();
            List<Product> newList = new ArrayList<>();

            Page<Transcation> transcations=transcationServiceImp.getPageTranscation(pageNumber);
            for (Transcation transcation : transcations.getContent()) {
                ResTransc newResTransc = new ResTransc();
                newResTransc.setId(transcation.getId());
                newResTransc.setTranscationId(transcation.getTranscationId());
                newResTransc.setDistributorCode(transcation.getDistributorCode());
                newResTransc.setTotalCost(transcation.getTotalCost());
                newResTransc.setStatus(transcation.getStatus());
                newResTransc.setDescription(transcation.getDescription());
                newResTransc.setCreatedAt(transcation.getCreatedAt());
                newResTransc.setUpdatedAt(transcation.getUpdatedAt());
                List<DetailTranscation> detailTranscation = transcation.getDetail();
                List<ResDetailTrans> resDetailTransList = new ArrayList<>();
                for(DetailTranscation newDetailTranscation : detailTranscation){
                    Product product = productsServiceImp.getProductByProductCode(newDetailTranscation.getProductId());
                    ResDetailTrans resDetailTrans = new ResDetailTrans();
                    resDetailTrans.setId(product.getId());
                    resDetailTrans.setProductId(product.getProductId());
                    resDetailTrans.setProductName(product.getProductName());
                    resDetailTrans.setDescription(product.getDescription());
                    resDetailTrans.setStatus(product.getStatus());
                    resDetailTrans.setImages(product.getImages());
                    resDetailTrans.setSize(product.getSize());
                    resDetailTrans.setColor(product.getColor());
                    resDetailTrans.setQuantity(product.getQuantity());
                    resDetailTrans.setQuantitySold(product.getQuantitySold());
                    resDetailTrans.setCost(product.getCost());
                    resDetailTrans.setCreatedAt(product.getCreatedAt());
                    resDetailTrans.setUpdatedAt(product.getUpdatedAt());
                    resDetailTrans.setNumber(newDetailTranscation.getNumber());
                    resDetailTrans.setDistributor(product.getDistributor());
                    resDetailTransList.add(resDetailTrans);
                }
                newResTransc.setDetail(resDetailTransList);
                resTransc.add(newResTransc);
            }
            return ResponseEntity.status(HttpStatus.OK).body(resTransc);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }
    }
    @GetMapping("/totalpage")
    public ResponseEntity<?> getTotalPageTrans(@RequestParam(value = "page", defaultValue = "0") String page){
        int pageNumber = Integer.parseInt(page);
        try {
            Page<Transcation> transcations=transcationServiceImp.getPageTranscation(pageNumber);
            return ResponseEntity.status(HttpStatus.OK).body(transcations.getTotalPages());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    // lấy sản phẩm theo id
    public ResponseEntity<?> getTransByid(@PathVariable("id") String id){
        int idNumber = Integer.parseInt(id);
        try {
            Transcation transcation=transcationServiceImp.getTransById(idNumber);
            if(transcation == null){
                Responese res = new Responese("Không tồn tại sản phẩm này", "4");
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            ResTransc newResTransc = new ResTransc();
            newResTransc.setId(transcation.getId());
            newResTransc.setTranscationId(transcation.getTranscationId());
            newResTransc.setDistributorCode(transcation.getDistributorCode());
            newResTransc.setTotalCost(transcation.getTotalCost());
            newResTransc.setStatus(transcation.getStatus());
            newResTransc.setDescription(transcation.getDescription());
            newResTransc.setCreatedAt(transcation.getCreatedAt());
            newResTransc.setUpdatedAt(transcation.getUpdatedAt());
            List<DetailTranscation> detailTranscation = transcation.getDetail();
            List<ResDetailTrans> resDetailTransList = new ArrayList<>();
            for(DetailTranscation newDetailTranscation : detailTranscation){
                Product product = productsServiceImp.getProductByProductCode(newDetailTranscation.getProductId());
                ResDetailTrans resDetailTrans = new ResDetailTrans();
                resDetailTrans.setId(product.getId());
                resDetailTrans.setProductId(product.getProductId());
                resDetailTrans.setProductName(product.getProductName());
                resDetailTrans.setDescription(product.getDescription());
                resDetailTrans.setStatus(product.getStatus());
                resDetailTrans.setImages(product.getImages());
                resDetailTrans.setSize(product.getSize());
                resDetailTrans.setColor(product.getColor());
                resDetailTrans.setQuantity(product.getQuantity());
                resDetailTrans.setQuantitySold(product.getQuantitySold());
                resDetailTrans.setCost(product.getCost());
                resDetailTrans.setCreatedAt(product.getCreatedAt());
                resDetailTrans.setUpdatedAt(product.getUpdatedAt());
                resDetailTrans.setNumber(newDetailTranscation.getNumber());
                resDetailTrans.setDistributor(product.getDistributor());
                resDetailTransList.add(resDetailTrans);
            }
            newResTransc.setDetail(resDetailTransList);
            return ResponseEntity.status(HttpStatus.OK).body(newResTransc);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }

    }
    @PutMapping("/status")
    public ResponseEntity<?> putStatus(@RequestBody statusDto statusDto){
        Transcation transcation=transcationServiceImp.getTransById(statusDto.getId());
        if(transcation == null){
            Responese res = new Responese("Không tồn tại sản phẩm này", "4");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        try {
            transcationServiceImp.browserTrans(statusDto.getId(),statusDto.getStatus(),statusDto.getDescription());
            return ResponseEntity.status(HttpStatus.OK).body("Thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }
    }
}
