package com.example.mockbe.controller;

import com.example.mockbe.dto.ResDistributor;
import com.example.mockbe.dto.Responese;
import com.example.mockbe.model.distributor.Distributor;
import com.example.mockbe.service.distributor.DistributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
@RequestMapping("/api/distributor")
public class DistributorController {
    @Autowired
    public DistributorService distributorService;

    private static final String EMAIL_PATTERN = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$";
    private static final String PHONE_PATTERN = "^0\\d{9,10}$";


    @PostMapping("/")
    // tạo nhà cung cấp
    public ResponseEntity<?> addCategory(@RequestBody Distributor distributor){
        if(!distributor.getEmail().matches(EMAIL_PATTERN)){
            Responese res = new Responese("Mail bạn nhập sai", "2");
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
        if(!distributor.getPhone().matches(PHONE_PATTERN)){
            Responese res = new Responese("Số điện thoại bạn nhập sai", "2");
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
        try {
            Distributor newDistributor = distributorService.createDistributor(distributor);
            if(newDistributor == null){
                Responese res = new Responese("Thông tin này đã tồn tại vui lòng nhập lại", "1");
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }
            return ResponseEntity.status(HttpStatus.OK).body(newDistributor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error post category: " + e.getMessage());
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> getDistributors(){
        try {
            List<Distributor> distributors=distributorService.getDistributors();
            return ResponseEntity.status(HttpStatus.OK).body(distributors);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }
    }
    @GetMapping("/page")
    public ResponseEntity<?> getPageDistributors(@RequestParam(value = "page", defaultValue = "0") String page){
        int pageNumber = Integer.parseInt(page);
        try {
            Page<Distributor> distributors=distributorService.getPageDistributors(pageNumber);
            return ResponseEntity.status(HttpStatus.OK).body(distributors.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }
    }
    @GetMapping("/totalpage")
    public ResponseEntity<?> getTotalPageDistributors(@RequestParam(value = "page", defaultValue = "0") String page){
        int pageNumber = Integer.parseInt(page);
        try {
            Page<Distributor> distributors=distributorService.getPageDistributors(pageNumber);
            return ResponseEntity.status(HttpStatus.OK).body(distributors.getTotalPages());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    // lấy sản phẩm theo id
    public ResponseEntity<?> getDistributorByid(@PathVariable("id") String id){
        int idNumber = Integer.parseInt(id);
        try {
            Distributor distributors=distributorService.getDistributorById(idNumber);
            if(distributors == null){
                Responese res = new Responese("Không tồn tại sản phẩm này", "4");
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            return ResponseEntity.status(HttpStatus.OK).body(distributors);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDistibutor (@PathVariable("id") String id,@RequestBody Distributor distributor){
        int idNumber = Integer.parseInt(id);
        try {
            ResDistributor distributors=distributorService.updateDistributor(distributor,idNumber);
            if(distributors == null){
                Responese res = new Responese("Không tồn tại sản phẩm này", "4");
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            if(distributors.getCode()== "1"){
                Responese res = new Responese("Error update distributors", "1");
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
            }
            return ResponseEntity.status(HttpStatus.OK).body(distributors.getDistribution());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error update distributors: " + e.getMessage());
        }
    }
    @PutMapping("/status")
    public ResponseEntity<?> updateDistibutorStatus (@RequestParam(value ="id") String id,@RequestBody Distributor distributor){
        int idNumber = Integer.parseInt(id);
        try {
            ResDistributor distributors=distributorService.updateDistributorStatus(distributor,idNumber);
            if(distributors == null){
                Responese res = new Responese("Không tồn tại sản phẩm này", "4");
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            if(distributors.getCode()== "1"){
                Responese res = new Responese("Error update distributors", "1");
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
            }
            return ResponseEntity.status(HttpStatus.OK).body(distributors.getDistribution());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error update distributors: " + e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDistibutor(@PathVariable("id") String id){
        int idNumber = Integer.parseInt(id);
        try{
            String code = distributorService.deleteDistributor(idNumber);
            if(code == "4"){
                Responese res = new Responese("Không tồn tại sản phẩm này", "4");
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            if(code == "1"){
                Responese res = new Responese("Error update distributors", "1");
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
            }
            Responese res = new Responese("Xóa sản phẩm thành công", "0");
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        catch (Exception e){
            Responese res = new Responese("Error update distributors", "1");
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }
}
