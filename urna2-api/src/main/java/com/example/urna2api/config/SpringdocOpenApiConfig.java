package com.example.urna2api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Gam Template Sirvice",
                version = "1.0",
                description = "Some custom description of API.",
                contact = @Contact(name = "IDIP", email = "", url = ""),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(url = "/", description = "Local server")
        },
        security = @SecurityRequirement(name = "JWT")
)
@SecuritySchemes({
        @SecurityScheme(
                name = "JWT",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer",
                bearerFormat = "JWT",
                in = SecuritySchemeIn.HEADER,
                paramName = "Authorization",
                description = "JWT token authentication"
        )
})
public class SpringdocOpenApiConfig {

}