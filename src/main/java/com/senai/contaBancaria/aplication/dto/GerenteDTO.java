package com.senai.contaBancaria.aplication.dto;

import com.senai.contaBancaria.domain.entity.Gerente;
import com.senai.contaBancaria.domain.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record GerenteDTO(
        @NotNull (message = "ID do Gerente é obrigatório")
        @Schema(description = "ID do Gerente", example = "321123")
        String id,
        @NotBlank (message = "Nome do Gerente é obrigatório")
        @Schema(description = "Nome do Gerente", example = "César")
        String nome,
        @NotNull (message = "CPF do gerente é obrigatório")
        @Schema(description = "CPF do gerente", example = "111.111.111-11")
        String cpf,
        @NotBlank (message = "Email do gerente é obrigatório")
        @Schema(description = "Email do gerente", example = "cesar@gmail.com")
        String email,
        @NotNull (message = "Senha é obrigatória")
        @Schema(description = "Senha é obrigatória", example = "985632147")
        String senha,
        @NotBlank (message = "Estar ativo é obrigatório")
        @Schema(description = "Ativo", example = "ativo")
        Boolean ativo,
        @NotNull Role role
) {
    public static GerenteDTO fromEntity(Gerente gerente) {
        return GerenteDTO.builder()
                .id(gerente.getId())
                .nome(gerente.getNome())
                .cpf(gerente.getCpf())
                .email(gerente.getEmail())
                .ativo(gerente.isAtivo())
                .role(gerente.getRole())
                .build();
    }

    public Gerente toEntity() {
        return Gerente.builder()
                .id(this.id)
                .nome(this.nome)
                .cpf(this.cpf)
                .email(this.email)
                .senha(this.senha)
                .ativo(this.ativo != null ? this.ativo : true)
                .role(this.role != null ? this.role : Role.GERENTE)
                .build();
    }

}