package com.senai.contaBancaria.aplication.dto;

import com.senai.contaBancaria.domain.entity.Cliente;
import com.senai.contaBancaria.domain.entity.Conta;
import com.senai.contaBancaria.domain.entity.ContaCorrente;
import com.senai.contaBancaria.domain.entity.ContaPoupanca;
import com.senai.contaBancaria.domain.exceptions.TipoDeContaInvalidaException;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ContaResumoDTO(
        @NotNull (message = "Número da conta é obrigatório")
        @Schema(description = "Número da conta", example = "5246")
        String numero,
        @NotBlank (message = "Tipo da conta é obrigatório")
        @Schema(description = "Tipo da conta", example = "Corrente")
        String tipo,
        @NotNull (message = "Saldo maior ou igual a R$0,00 é obrigatório")
        @Schema(description = "Saldo em conta", example = "120.00")
        BigDecimal saldo
) {
    public Conta toEntity(Cliente cliente) {
        if ("CORRENTE".equalsIgnoreCase(tipo)) {
            return ContaCorrente.builder()
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .ativa(true)
                    .cliente(cliente)
                    .limite(new BigDecimal("500.0"))
                    .taxa(new BigDecimal("0.05"))
                    .build();
        } else if ("POUPANCA".equalsIgnoreCase(tipo)) {
            return ContaPoupanca.builder()
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .ativa(true)
                    .rendimento(new BigDecimal("0.01"))
                    .cliente(cliente)
                    .build();
        }
        throw new TipoDeContaInvalidaException();
    }
    public static ContaResumoDTO fromEntity(Conta c){
        return new ContaResumoDTO(
                c.getNumero(),
                c.getTipo(),
                c.getSaldo()
        );
    }
}