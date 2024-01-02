package com.example.mockbe.model.receipt;


import jakarta.persistence.*;

@Entity
@Table(name = "receipt_details")
public class ReceiptDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


}
