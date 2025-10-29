package com.senai.contaBancaria.aplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ValorSaqueDepositoDTO(
        @NotNull (message = "Valor de saque é obrigatório")
        @Schema(description = "Valor para Saque", example = "R$5,00")
        BigDecimal valor

) {
}
