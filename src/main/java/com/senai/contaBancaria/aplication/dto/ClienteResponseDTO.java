package com.senai.contaBancaria.aplication.dto;

import com.senai.contaBancaria.domain.entity.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ClienteResponseDTO(
        @NotNull (message = "ID é obrigatório")
        @Schema(description = "ID do Cliente", example = "01")
        String id,
        @NotBlank (message = "Nome é obrigatório")
        @Schema(description = "Nome", example = "Lara Santos")
        String nome,
        @NotNull (message = "CPF é obrigatório")
        @Schema(description = "CPF", example = "000.000.000-00")
        String cpf,
        List<ContaResumoDTO> contas
) {
    public static ClienteResponseDTO fromEntity(Cliente cliente) {
    List<ContaResumoDTO>contas = cliente.getContas().stream()
            .map(ContaResumoDTO::fromEntity)
            .toList();
    return new ClienteResponseDTO(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            contas
    );
    }
}