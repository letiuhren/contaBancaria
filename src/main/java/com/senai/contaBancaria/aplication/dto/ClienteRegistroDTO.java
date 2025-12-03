package com.senai.contaBancaria.aplication.dto;

import com.senai.contaBancaria.domain.entity.Cliente;
import com.senai.contaBancaria.domain.entity.Conta;
import com.senai.contaBancaria.domain.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public record ClienteRegistroDTO(
        @NotBlank (message = "Nome é obrigatório")
        @Schema(description = "Nome", example = "Lara Santos")
        String nome,
        @NotNull (message = "CPF é obrigatório")
        @Schema(description = "CPF", example = "000.000.000-00")
        String cpf,
        @NotBlank (message = "Email é obigatório")
        @Schema(description = "Email", example = "lara@gmail.com")
        String email,
        @NotBlank (message = "Senha é obrigatória")
        @Schema(description = "Senha", example = "1254125412544")
        String senha,



        ContaResumoDTO contaDTO
) {
    public Cliente toEntity() {
        return Cliente.builder()
                .ativo(true)
                .nome(this.nome)
                .cpf(this.cpf)
                .email(this.email)
                .senha(this.senha)
                .role(Role.CLIENTE)
                .contas(new ArrayList<>())
                .build();
    }
}
