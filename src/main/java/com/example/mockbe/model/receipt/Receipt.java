package com.example.mockbe.model.receipt;

import jakarta.persistence.*;

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String receiptCode;
    private String implementer;
    private Integer totalPrice;


    @Enumerated(EnumType.STRING)
    private Status status;
}
