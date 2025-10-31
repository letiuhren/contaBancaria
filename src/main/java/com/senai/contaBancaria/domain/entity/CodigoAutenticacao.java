package com.senai.contaBancaria.domain.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity

public class CodigoAutenticacao {
    @NotNull String id;
    @NotNull String codigo;
    @NotNull Date expiraEm;
    @NotNull Boolean validade;
    @NotBlank Cliente cliente;

}
