package com.senai.contaBancaria.apliation.dto;

import com.senai.contaBancaria.domain.entity.Conta;
import com.senai.contaBancaria.domain.entity.ContaCorrente;

public record ContaResumoDTO(
        String numero,
        String tipo,
        int saldo
){
    public Conta toEntity(){
        if ("CORRENTE".equalsIgnoreCase(tipo)){
            return ContaCorrente.builder()
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .ative(true)
                    .build();
        }
    }
}
