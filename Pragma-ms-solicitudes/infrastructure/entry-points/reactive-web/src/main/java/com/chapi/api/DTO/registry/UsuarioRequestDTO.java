package com.chapi.api.DTO.registry;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request payload to create a new user")
public record UsuarioRequestDTO(

        @Schema(description = "First name", example = "Juan", requiredMode = Schema.RequiredMode.REQUIRED)
        String nombre,

        @Schema(description = "Last name", example = "Perez", requiredMode = Schema.RequiredMode.REQUIRED)
        String apellido,

        @Schema(description = "Email address", example = "juan.perez@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
        String email,

        @Schema(description = "National ID", example = "12345678", requiredMode = Schema.RequiredMode.REQUIRED)
        String documentoIdentidad,

        @Schema(description = "Phone number", example = "+51 999888777")
        String telefono,

        @Schema(description = "Base salary", example = "6000")
        Long salarioBase
) {}