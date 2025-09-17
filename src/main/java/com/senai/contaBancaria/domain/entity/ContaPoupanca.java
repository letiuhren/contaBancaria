package com.senai.contaBancaria.domain.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public class ContaPoupanca extends Conta{

    private double rendimento;
}
