package com.example.urna2api.sercurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataTokenJtw {
    private String token;
    private String refreshToken;

}