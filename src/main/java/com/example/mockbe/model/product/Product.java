package com.example.mockbe.model.product;
import java.util.List;

import com.example.mockbe.model.distributor.Distributor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productCode;
    private String productName;
    private Integer totalQuantity;
    private String unit;
    private Integer importPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany( mappedBy = "product")
    private List<ProductVariant> productVariantsList;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "distributor_id", referencedColumnName = "id")
    private Distributor distributor;

}
