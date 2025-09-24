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
@DiscriminatorValue("CORRENTE")
@Data
@NoArgsConstructor
@SuperBuilder
public class ContaCorrente extends Conta {
    @Column(precision = 19, scale = 2)
    private BigDecimal limite;
    @Column (precision = 19, scale = 2)
    private BigDecimal taxa;

    @Override
    public String getTipo() {
        return "CORRENTE";
    }
}
