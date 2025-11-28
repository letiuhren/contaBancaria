package com.senai.contaBancaria.aplication.dto;

import com.senai.contaBancaria.domain.entity.Conta;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PagamentoRequestDTO (

        @NotNull(message = "Conta é obrigatório")
        @Schema(description = "Especificar a conta", example = "Poupança")
        Conta conta,

        @NotNull(message = "Descrição do pagamento é obrigatória")
        @Schema(description = "Informar o nome do pagamento", example = "Agua")
        String descricaoPagamento,

        @NotNull(message = "Valor pago é obrigatório")
        @Schema(description = "Informar o valor pago", example = "R$ 50,00")
        String valorPago,

        @NotNull(message = "vencimento do pagamento é obrigatório")
        @Schema(description = "Informar a vencimento do pagamento", example = "03-09-2025")
        String dataVencimento

){
}
