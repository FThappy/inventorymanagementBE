package com.example.mockbe.dto;

import com.example.mockbe.model.product.Image;
import com.example.mockbe.model.product.Statuss;
import com.example.mockbe.model.transcation.DetailTranscation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResDetailTrans {
    private Long id;
    private String productId;
    private String productName;
    private String description;
    private Statuss status;
    private List<Image> images;
    private String size;
    private String color;
    private Integer quantity;
    private Integer quantitySold;
    private Double cost;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String distributor;
    private int number;
}
