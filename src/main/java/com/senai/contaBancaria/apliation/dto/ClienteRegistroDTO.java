package com.senai.contaBancaria.apliation.dto;

import com.senai.contaBancaria.domain.entity.Cliente;
import com.senai.contaBancaria.domain.entity.Conta;

import java.util.ArrayList;

public record ClienteRegistroDTO(
        String nome,
        String cpf,
        ContaResumoDTO contaDTO
) {
    public  Cliente toEntity(){
        return Cliente.builder()
                .ativo(true)
                .nome(this.nome)
                .cpf(this.cpf)
                .contas(new ArrayList<Conta>())
                .build();
    }
}
