package com.example.urna2api.sercurity.dto;

import java.time.Instant;

public class TokenData {
    private final Instant expirationTime;
    private final String username;

    public TokenData(Instant expirationTime, String username) {
        this.expirationTime = expirationTime;
        this.username = username;
    }

    public Instant getExpirationTime() {
        return expirationTime;
    }

    public String getUsername() {
        return username;
    }
}
