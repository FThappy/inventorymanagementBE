package com.example.mockbe.dto;

import com.example.mockbe.model.receipt.Receipt;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResReceipt {

    private String code;
    private Receipt receipt;

}
