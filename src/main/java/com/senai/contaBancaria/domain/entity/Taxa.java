package com.senai.contaBancaria.domain.entity;


import com.senai.contaBancaria.aplication.dto.TaxaDTO;
import com.senai.contaBancaria.domain.enums.TipoPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@SuperBuilder

public class Taxa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @NotNull

    private String id;
    @NotBlank

    private String descricao;
    @NotNull

    private BigDecimal percentual;
    @NotNull

    private BigDecimal valorFixo;

    private TipoPagamento tipoPag;


}
