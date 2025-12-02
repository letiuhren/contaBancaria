package com.senai.contaBancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class DispositivoIOT  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @NotNull  int codigoSerial;
    @NotNull  String chavePublica;

    @OneToOne
    @JoinColumn(name = "cliente_id", unique = true)
    private Cliente cliente;
}
