package com.senai.contaBancaria.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class DispositivoIOT  {
    @NotNull  String id;
    @NotNull  int codigoSerial;
    @NotNull  String chavePublica;
    @NotBlank Boolean ativo;

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;
}
