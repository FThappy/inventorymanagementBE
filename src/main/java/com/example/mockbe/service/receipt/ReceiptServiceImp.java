package com.example.mockbe.service.receipt;

import com.example.mockbe.dto.ResReceipt;
import com.example.mockbe.model.receipt.Receipt;
import com.example.mockbe.model.receipt.ReceiptDetails;
import com.example.mockbe.model.receipt.Status;
import com.example.mockbe.repository.ReceiptRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ReceiptServiceImp implements ReceiptService {

    @Autowired
    public ReceiptRepository receiptRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Receipt createReceipt(Receipt receipt) {
        try {
            Receipt newReceipt = new Receipt();
            newReceipt.setReceiptCode(receipt.getReceiptCode());
            newReceipt.setImplementer(receipt.getImplementer());
            newReceipt.setStatus(Status.unpaid);
            newReceipt.setCreatedAt(LocalDateTime.now());
            newReceipt.setUpdatedAt(LocalDateTime.now());
            entityManager.persist(newReceipt);

            List<ReceiptDetails> receiptDetailsList = receipt.getReceiptDetailsList();
            for (ReceiptDetails receiptDetails : receiptDetailsList) {
                receiptDetails.setReceipt(newReceipt);
                entityManager.persist(receiptDetails);
            }

            return newReceipt;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ResReceipt updateReceipt(Receipt receipt, int id) {
        Receipt oldReceipt = receiptRepository.findById(id).orElse(null);
        if (oldReceipt == null) {
            return null;
        }
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();

            oldReceipt.setId(id);
            oldReceipt.setReceiptCode(receipt.getReceiptCode());
            oldReceipt.setImplementer(receipt.getImplementer());
            oldReceipt.setUpdatedAt(currentDateTime);

            List<ReceiptDetails> oldReceiptDetailsList = oldReceipt.getReceiptDetailsList();
            List<ReceiptDetails> newReceiptDetailsList = receipt.getReceiptDetailsList();

            // Update existing receipt details
            for (int i = 0; i < oldReceiptDetailsList.size(); i++) {
                ReceiptDetails oldReceiptDetails = oldReceiptDetailsList.get(i);
                ReceiptDetails newReceiptDetails = newReceiptDetailsList.get(i);

                oldReceiptDetails.setQuantity(newReceiptDetails.getQuantity());
                oldReceiptDetails.setPrice(newReceiptDetails.getPrice());
            }

            // Add new receipt details
            for (int i = oldReceiptDetailsList.size(); i < newReceiptDetailsList.size(); i++) {
                ReceiptDetails newReceiptDetails = newReceiptDetailsList.get(i);
                newReceiptDetails.setReceipt(oldReceipt);
                oldReceiptDetailsList.add(newReceiptDetails);
                entityManager.persist(newReceiptDetails);
            }

            // Remove deleted receipt details
            for (int i = newReceiptDetailsList.size(); i < oldReceiptDetailsList.size(); i++) {
                ReceiptDetails oldReceiptDetails = oldReceiptDetailsList.get(i);
                oldReceiptDetailsList.remove(oldReceiptDetails);
                entityManager.remove(oldReceiptDetails);
            }

            entityManager.merge(oldReceipt); // Update the modified receipt in the database

            return new ResReceipt("success", oldReceipt);
        } catch (Exception e) {
            return new ResReceipt("error", null);
        }
    }

    @Override
    public List<Receipt> getReceipts() {
        return receiptRepository.findAll();
    }

    @Override
    public Receipt geyReceiptById(int id) {
        return receiptRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Receipt> getPageReceipt(int page) {
        return receiptRepository.getPageReceipt(page);
    }

    @Override
    public String deleteReceipt(int id) {
        Receipt receipt = receiptRepository.findById(id).orElse(null);

        if (receipt == null) {
            return "4";
        }
        try {
            receiptRepository.deleteById(id);
            return "0";
        } catch (Exception e) {
            return "1";
        }
    }

    @Override
    public ResReceipt updateReceiptStatus(Receipt receipt, int id) {
        Receipt oldReceipt = receiptRepository.findById(id).orElse(null);
        if (oldReceipt == null) {
            return null;
        }
        try {
            Receipt newReceipt;
            oldReceipt.setStatus(receipt.getStatus());
            newReceipt = receiptRepository.save(oldReceipt);
            ResReceipt resReceipt = new ResReceipt("0",newReceipt);
            return resReceipt;
        } catch (Exception e){
            ResReceipt resReceipt = new ResReceipt("1",null);
            return resReceipt;
        }
    }
}
