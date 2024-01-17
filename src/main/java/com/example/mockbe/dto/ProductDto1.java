package com.example.mockbe.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto1 {
    private String productId;
    private String productName;
    private String description;
    private String size;
    private String color;
    private String quantity;
    private String cost;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String distributor_code;
}
