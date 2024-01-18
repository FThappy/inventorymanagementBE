package com.example.mockbe.dto;

import com.example.mockbe.model.transcation.Statusss;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResTransc {
    private int id;
    private String transcationId;
    private String distributorCode;
    private double totalCost;
    private Statusss status;
    private String description;
    List<ResDetailTrans> detail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
