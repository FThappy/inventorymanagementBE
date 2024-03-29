package com.example.mockbe.dto;

import com.example.mockbe.model.distributor.Distributor;
import com.example.mockbe.model.product.Image;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productId;
    private String productName;
    private String description;
    private List<MultipartFile> imageFiles;
    private String size;
    private String color;
    private String quantity;
    private String cost;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String distributor_code;
    private String quantitySold;

}
