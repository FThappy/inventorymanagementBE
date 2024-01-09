package com.example.mockbe.service.receipt;


import com.example.mockbe.dto.ResReceipt;
import com.example.mockbe.model.receipt.Receipt;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReceiptService {
    Receipt createReceipt(Receipt receipt);

    ResReceipt updateReceipt (Receipt receipt,int id);

    List<Receipt> getReceipts();

    Receipt geyReceiptById(int id);

    Page<Receipt> getPageReceipt(int page);

    String deleteReceipt(int id);

    ResReceipt updateReceiptStatus(Receipt receipt, int id);

}
