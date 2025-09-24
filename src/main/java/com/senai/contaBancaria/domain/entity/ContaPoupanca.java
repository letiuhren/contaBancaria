package com.senai.contaBancaria.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
@DiscriminatorValue("POUPANCA")
@SuperBuilder
@NoArgsConstructor

public class ContaPoupanca extends Conta{

    @Column(precision = 19, scale = 2)
    private BigDecimal rendimento;

    @Override
    public String getTipo() {
        return "POUPANCA";
    }
}
