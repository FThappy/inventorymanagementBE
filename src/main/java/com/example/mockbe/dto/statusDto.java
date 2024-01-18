package com.example.mockbe.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class statusDto {
    private  int id;
    private String status;
    private String description;
}
