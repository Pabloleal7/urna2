package com.example.urna2api.sercurity;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;


@Service
public class RefreshTokenService {

    @Autowired
    private JwtConfig jwtConfig;

    public String generateRefreshToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
        String refreshToken = JWT.create()
                .withSubject(username)
                .withExpiresAt(Date.from(Instant.now().plusSeconds(jwtConfig.getRefreshExpiration())))
                .sign(algorithm);
        return refreshToken;
    }

    public boolean validateRefreshToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromRefreshToken(String refreshToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
            DecodedJWT jwt = JWT.require(algorithm).build().verify(refreshToken);
            return jwt.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
