package com.senai.contaBancaria.aplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ContaAtualizacaoDTO(
        @NotNull (message = "Limite é obrigatório")
        @Schema(description = "Limite", example = "Troca de óleo sintético")
        BigDecimal limite

) {
}
