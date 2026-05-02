package com.example.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String tipo = "Bearer";
    private String email;
    private String role;
    private long expiresIn;

    public AuthResponse(String token, String email, String role, long expiresIn) {
        this.token    = token;
        this.email    = email;
        this.role     = role;
        this.expiresIn = expiresIn;
    }
}
