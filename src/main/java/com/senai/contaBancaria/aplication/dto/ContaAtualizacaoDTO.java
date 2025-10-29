package com.senai.contaBancaria.aplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ContaAtualizacaoDTO(
        @NotNull (message = "Saldo igual ou maior que R$0,00 é obrigatório")
        @Schema(description = "Saldo", example = "Troca de óleo sintético")
        BigDecimal saldo,
        @NotNull (message = "Limite é obrigatório")
        @Schema(description = "Limite", example = "Troca de óleo sintético")
        BigDecimal limite,
        @NotNull (message = "Rendimento é obrigatório")
        @Schema(description = "Rendimento", example = "0.75%")
        BigDecimal rendimento,
        @NotNull (message = "Taxa é obrigatória")
        @Schema(description = "Taxa de operações da conta corrente", example = "R$3.00")
        BigDecimal taxa
) {
}
