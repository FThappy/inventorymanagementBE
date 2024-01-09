package com.example.mockbe.model.receipt;


import com.example.mockbe.model.product.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "receipt_details")
public class ReceiptDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Receipt receipt;

    @OneToOne
    private Product product;

    private Integer quantity;
    private Integer price;
}
