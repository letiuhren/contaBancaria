package com.senai.contaBancaria.aplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PagamentDTO (
        @NotNull(message = "Id é obrigatório")
        @Schema(description = "Id", example = "01")
        String id,

        @NotNull(message = "Conta é obrigatório")
        @Schema(description = "Especificar a conta", example = "Poupança")
        String conta,

        @NotNull(message = "Boleto é obrigatório")
        @Schema(description = "Informar o numero do boleto", example = "2191292992")
        String boleto,

        @NotNull(message = "Valor pago é obrigatório")
        @Schema(description = "Informar o valor pago", example = "R$ 50,00")
        String valorPago,

        @NotNull(message = "Data do pagamento é obrigatório")
        @Schema(description = "Informar a data do pagamento", example = "01-09-2025")
        String dataPagamento,

        @NotNull(message = "Status é obrigatório")
        @Schema(description = "Informar o status do pagamento", example = "Pagamento efetuado com sucesso")
        Enum status,

        @NotNull(message = "Taxa é obrigatório")
        @Schema(description = "Taxa do pagamento", example = "R$ 3.00")
        BigDecimal taxa
){
}
