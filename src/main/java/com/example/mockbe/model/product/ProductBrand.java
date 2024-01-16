package com.example.mockbe.model.product;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_brands")
public class ProductBrand {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, unique = true)
  private String name;
}