package com.senai.contaBancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity

public class Pagamento {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    String conta;

    @NotBlank
    String boleto;

    @NotNull
    String valorPago;

    @NotNull
    String dataPagamento;

    @NotBlank
    String status;

    @NotNull
    BigDecimal taxa;

}
