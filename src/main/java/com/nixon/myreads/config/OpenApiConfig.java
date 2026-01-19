package com.nixon.myreads.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@OpenAPIDefinition(
        info = @Info(
                title = "My Reads API",
                description = "API for reading tracking"
        ),
        security = {
                @SecurityRequirement(
                        name = "Bearer Authentication"
                )
        }
)
public class OpenApiConfig {
}
