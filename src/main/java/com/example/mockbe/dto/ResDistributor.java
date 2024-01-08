package com.example.mockbe.dto;

import com.example.mockbe.model.distributor.Distributor;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResDistributor {

    private String code;
    private Distributor distribution;


}
