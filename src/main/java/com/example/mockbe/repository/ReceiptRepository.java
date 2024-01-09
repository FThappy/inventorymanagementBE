package com.example.mockbe.repository;

import com.example.mockbe.model.receipt.Receipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {

    default Page<Receipt> getPageReceipt(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return findAll(pageable);
    }

    Receipt findByReceiptCode(String receiptCode);
}
