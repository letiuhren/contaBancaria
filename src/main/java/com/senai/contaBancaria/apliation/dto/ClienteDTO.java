package com.senai.contaBancaria.apliation.dto;

import com.senai.contaBancaria.domain.entity.Cliente;
import com.senai.contaBancaria.domain.entity.Conta;

public record ClienteDTO (
        String id,
        String nome,
        Long cpf
) {
    public static ClienteDTO fromEntity (Cliente cliente){
        if (cliente == null) return null;
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf()
        );
    }
    public Cliente toEntity (Conta conta){
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);
        return cliente;
    }
}
