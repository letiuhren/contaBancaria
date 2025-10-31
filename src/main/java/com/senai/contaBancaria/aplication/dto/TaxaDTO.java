package com.senai.contaBancaria.aplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record TaxaDTO (
        @NotNull(message = "ID da taxa é obrigatória")
        @Schema(description = "Id da taxa", example = "07")
        String id,
        @NotBlank(message = "Descrição é obrigatória")
        @Schema(description = "Nome da taxa", example = "Tarifa Bancária")
        String descricao,

) {

}
