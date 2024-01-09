package com.example.mockbe.controller;

import com.example.mockbe.dto.ResDistributor;
import com.example.mockbe.dto.ResReceipt;
import com.example.mockbe.dto.Responese;
import com.example.mockbe.model.distributor.Distributor;
import com.example.mockbe.model.receipt.Receipt;
import com.example.mockbe.repository.ReceiptRepository;
import com.example.mockbe.service.receipt.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
@RequestMapping("/api/receipt")
public class ReceiptController {

    @Autowired
    public ReceiptService receiptService;

    @Autowired
    public ReceiptRepository receiptRepository;

    @PostMapping("/")
    public ResponseEntity<?> addNewReceipt(@RequestBody Receipt receipt) {
        try {
            String receiptCode = receipt.getReceiptCode();
            Receipt existingReceipt = receiptRepository.findByReceiptCode(receiptCode);
            if (existingReceipt != null) {
                Responese res = new Responese("Mã biên nhận đã tồn tại. Vui lòng nhập một mã khác.", "1");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }

            Receipt newReceipt = receiptService.createReceipt(receipt);
            if (newReceipt == null) {
                Responese res = new Responese("Đã xảy ra lỗi khi tạo biên nhận.", "1");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }
            return ResponseEntity.status(HttpStatus.OK).body(newReceipt);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi thêm biên nhận: " + e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getReceipt(){
        try {
            List<Receipt> receipts=receiptService.getReceipts();
            return ResponseEntity.status(HttpStatus.OK).body(receipts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }
    }

    @GetMapping("/page")
    public ResponseEntity<?> getPageReceipts(@RequestParam(value = "page", defaultValue = "0") String page){
        int pageNumber = Integer.parseInt(page);
        try {
            Page<Receipt> receipts=receiptService.getPageReceipt(pageNumber);
            return ResponseEntity.status(HttpStatus.OK).body(receipts.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }
    }

    @GetMapping("/totalpage")
    public ResponseEntity<?> getTotalPageReceipts(@RequestParam(value = "page", defaultValue = "0") String page){
        int pageNumber = Integer.parseInt(page);
        try {
            Page<Receipt> receipts = receiptService.getPageReceipt(pageNumber);
            return ResponseEntity.status(HttpStatus.OK).body(receipts.getTotalPages());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    // lấy sản phẩm theo id
    public ResponseEntity<?> getReceiptByid(@PathVariable("id") String id){
        int idNumber = Integer.parseInt(id);
        try {
            Receipt receipt = receiptService.geyReceiptById(idNumber);
            if(receipt == null){
                Responese res = new Responese("Không tồn tại sản phẩm này", "4");
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            return ResponseEntity.status(HttpStatus.OK).body(receipt);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get distributors: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReceipt (@PathVariable("id") String id,@RequestBody Receipt receipt){
        int idNumber = Integer.parseInt(id);
        try {
            ResReceipt resReceipt = receiptService.updateReceipt(receipt,idNumber);
            if(resReceipt == null){
                Responese res = new Responese("Không tồn tại sản phẩm này", "4");
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            if(resReceipt.getCode()== "1"){
                Responese res = new Responese("Error update distributors", "1");
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
            }
            return ResponseEntity.status(HttpStatus.OK).body(resReceipt.getReceipt());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error update distributors: " + e.getMessage());
        }
    }

    @PutMapping("/status")
    public ResponseEntity<?> updateReceiptStatus (@RequestParam(value ="id") String id,@RequestBody Receipt receipt){
        int idNumber = Integer.parseInt(id);
        try {
            ResReceipt resReceipt= receiptService.updateReceiptStatus(receipt,idNumber);
            if(resReceipt == null){
                Responese res = new Responese("Không tồn tại sản phẩm này", "4");
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            if(resReceipt.getCode() == "1"){
                Responese res = new Responese("Error update resReceipt", "1");
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
            }
            return ResponseEntity.status(HttpStatus.OK).body(resReceipt.getReceipt());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error update distributors: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReceipt(@PathVariable("id") String id){
        int idNumber = Integer.parseInt(id);
        try{
            String code = receiptService.deleteReceipt(idNumber);
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
