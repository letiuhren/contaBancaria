package com.senai.contaBancaria.domain.entity;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Taxa {
    @NotNull

    String id;
    @NotBlank

    String descricao;
    @NotNull

    Boolean percentual;
    @NotNull

    Boolean valorFixo;
}
