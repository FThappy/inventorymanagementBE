package com.example.mockbe.dto;


import com.example.mockbe.model.distributor.Distributor;
import com.example.mockbe.model.product.Product;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResProduct {
    private String code;
    private Product product;
}
