package com.senai.contaBancaria.aplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferenciaDTO(
        @NotNull (message = "Valor à ser transferido é obrigatório")
        @Schema(description = "Valor de transferencia", example = "R$75,00")
        BigDecimal valor,
        @NotBlank (message = "Conta de destino é obrigatória")
        @Schema(description = "Conta de destino", example = "542658-7")
        String contaDestino
) {
}
