package com.senai.contaBancaria.aplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AuthDTO {

    public record LoginRequest(
            @NotBlank (message = "Email é obrigatório")
            @Schema (description = "Email", example = "lara@gmail.com")
            String email,
            @NotNull (message = "Senha é obrigatória")
            @Schema(description = "Senha", example = "7765893214")
            String senha
    ) {}
    public record TokenResponse(
            @NotBlank (message = "Token é obrigatório")
            @Schema(description = "Token", example = "t1rhd31h53r1h654tr5d3hr5y1r65t4yu6ty51h5")
            String token
    ) {}
}
