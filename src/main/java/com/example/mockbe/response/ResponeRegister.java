package com.example.mockbe.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponeRegister {
    @JsonProperty("info")
    private String info;
    @JsonProperty("code")
    private String code;
}
