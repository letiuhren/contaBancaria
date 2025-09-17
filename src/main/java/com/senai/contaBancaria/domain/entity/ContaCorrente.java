package com.senai.contaBancaria.domain.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public class ContaCorrente extends Conta {

    private Long limite;

    private double taxa;
}
