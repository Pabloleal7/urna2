package com.example.urna2api.sercurity.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;


}