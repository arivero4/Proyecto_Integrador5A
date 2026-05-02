package com.example.inspecciones.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title       = "MS-Inspecciones API",
        version     = "1.0",
        description = "Gestión de Inspecciones Fitosanitarias, Plagas y Resultados. " +
                      "Requiere token JWT del MS-Usuarios (POST /auth/login en puerto 8081)."
    )
)
@SecurityScheme(
    name            = "bearerAuth",
    type            = SecuritySchemeType.HTTP,
    scheme          = "bearer",
    bearerFormat    = "JWT",
    description     = "Ingresa el token JWT obtenido en MS-Usuarios /auth/login"
)
public class OpenApiConfig {}
