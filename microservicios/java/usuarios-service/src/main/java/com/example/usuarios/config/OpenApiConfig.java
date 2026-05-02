package com.example.usuarios.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title       = "MS-Usuarios API",
        version     = "1.0",
        description = "Gestión de Usuarios, Productores y Asistentes Técnicos. " +
                      "Usa POST /auth/register para crear cuenta, POST /auth/login para obtener token JWT."
    )
)
@SecurityScheme(
    name            = "bearerAuth",
    type            = SecuritySchemeType.HTTP,
    scheme          = "bearer",
    bearerFormat    = "JWT",
    description     = "Ingresa el token JWT obtenido en /auth/login"
)
public class OpenApiConfig {}
