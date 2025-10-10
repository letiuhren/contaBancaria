package com.senai.contaBancaria.aplication.dto;

import com.senai.contaBancaria.domain.entity.Cliente;
import com.senai.contaBancaria.domain.entity.Conta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public record ClienteRegistroDTO(
        @NotBlank
        String nome,
        @NotNull
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
