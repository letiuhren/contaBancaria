package com.senai.contaBancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity


public class CodigoAutenticacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @NotNull String codigo;
    @NotNull Date expiraEm;
    @NotNull Boolean validade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_codigoaut_cliente"))
    private Cliente cliente;
}
