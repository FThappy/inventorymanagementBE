package com.example.mockbe.model.supplier;

import com.example.mockbe.model.product.Product;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String supplierName;
    private String supplierCode;
    private Integer phone;
    private String email;
    private String address;
    private Date createdAt;
    private Date updateAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "supplier")
    private List<Product> productList;
}
