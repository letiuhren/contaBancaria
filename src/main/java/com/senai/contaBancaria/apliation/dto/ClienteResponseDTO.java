package com.senai.contaBancaria.apliation.dto;

public record ClienteResponseDTO(
        String id,
        String nome,
        String cpf,
        List <ContaResumoDTO> contas
) {
}
