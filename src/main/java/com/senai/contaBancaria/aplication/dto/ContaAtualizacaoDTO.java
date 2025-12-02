package com.senai.contaBancaria.aplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ContaAtualizacaoDTO(
        @Schema(description = "Novo Saldo", example = "1500.00")
        BigDecimal saldo,

        @Schema(description = "Novo Limite", example = "500.00")
        BigDecimal limite,

        @Schema(description = "Nova Taxa", example = "0.05")
        BigDecimal taxa,

        @Schema(description = "Novo Rendimento", example = "0.005")
        BigDecimal rendimento
) {
}
