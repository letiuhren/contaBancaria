package com.senai.contaBancaria.apliation.dto;

import com.senai.contaBancaria.domain.entity.Cliente;
import com.senai.contaBancaria.domain.entity.Conta;

public record ContaDTO(
        String id,
        String numero,
        int saldo
) {
    public static ContaDTO fromEntity (Conta conta) {
        if (conta == null) return null;

        return new ContaDTO(
                conta.getId(),
                conta.getNumero(),
                conta.getSaldo()
        );
    }

    public Conta toEntity (Cliente cliente){
        Conta conta = new Conta();
        conta.setId(this.id);
        conta.setNumero(this.numero);
        conta.setSaldo(this.saldo);
        return conta;
    }
}
