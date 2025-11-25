package com.senai.contaBancaria.aplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TaxaResponseDTO (
        @NotNull(message = "ID da taxa recebido")
        @Schema(description = "Id da taxa", example = "07")
        String id,
        @NotBlank(message = "Tipo de taxa")
        @Schema(description = "Nome da taxa", example = "Tarifa Bancária")
        String descricao,
        @NotNull (message = "Percentual é obrigatório" )
        @Schema(description = "Percentual da taxa aplicada",example = "0.05")
        BigDecimal percentual,
        @NotNull (message = "Valor fixo é obrigatório" )
        @Schema(description = "Valor fixo da taxa aplicada",example = "3,00")
        BigDecimal valorFixo
){
}
