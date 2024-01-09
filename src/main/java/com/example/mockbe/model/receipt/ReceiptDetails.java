package com.example.mockbe.model.receipt;


import com.example.mockbe.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "receipt_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    private Receipt receipt;

    @OneToOne
    private Product product;
    private Integer quantity;
    private Integer price;
}
