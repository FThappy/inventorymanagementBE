package com.example.mockbe.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranscationDto {
    private String transcationId;
    private String distributorCode;
    private double totalCost;
    private List<DetailTransDto> product;

}
