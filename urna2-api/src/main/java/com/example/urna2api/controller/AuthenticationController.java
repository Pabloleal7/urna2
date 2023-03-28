package com.example.urna2api.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.urna2api.sercurity.JwtConfig;
import com.example.urna2api.sercurity.RefreshTokenService;

import com.example.urna2api.sercurity.dto.AuthenticationRequest;
import com.example.urna2api.sercurity.dto.DataTokenJtw;
import com.example.urna2api.sercurity.dto.RefreshTokenRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {


    private final JwtConfig jwtConfig;

    private final UserDetailsService userDetailsService;

    private final RefreshTokenService refreshTokenService;

    public AuthenticationController(JwtConfig jwtConfig, UserDetailsService userDetailsService, RefreshTokenService refreshTokenService) {
        this.jwtConfig = jwtConfig;

        this.userDetailsService = userDetailsService;

        this.refreshTokenService = refreshTokenService;
    }


    @PostMapping("/login")
    public ResponseEntity<DataTokenJtw> login(@RequestBody AuthenticationRequest loginRequest) {
         userDetailsService.loadUserByUsername(loginRequest.getUsername());

        String username = loginRequest.getUsername();
        String accessToken = generateAccessToken(username);
        String refreshToken = refreshTokenService.generateRefreshToken(username);
        return ResponseEntity.ok(new DataTokenJtw(accessToken, refreshToken));


    }

    @RequestMapping("/me")
    public ResponseEntity<UserDetails> me(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<DataTokenJtw> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();

        if (refreshToken == null || !refreshTokenService.validateRefreshToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            String username = refreshTokenService.getUsernameFromRefreshToken(refreshToken);
            String newAccessToken = generateAccessToken(username);
            String newRefreshToken = refreshTokenService.generateRefreshToken(username);
            return ResponseEntity.ok(new DataTokenJtw(newAccessToken, newRefreshToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



    private String generateAccessToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfig.getExpiration() * 1000))
                .sign(algorithm);
    }

}
