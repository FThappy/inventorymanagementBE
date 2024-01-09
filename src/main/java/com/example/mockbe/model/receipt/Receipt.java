package com.example.mockbe.model.receipt;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String receiptCode;
    private String implementer;
    private Integer totalPrice;

    @OneToMany(mappedBy = "receipt")
    private List<ReceiptDetails> receiptDetailsList;

    @Enumerated(EnumType.STRING)
    private Status status;
}
