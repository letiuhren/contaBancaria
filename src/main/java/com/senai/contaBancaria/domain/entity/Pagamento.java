package com.senai.contaBancaria.domain.entity;

import com.senai.contaBancaria.domain.enums.StatusPagamento;
import com.senai.contaBancaria.domain.enums.TipoPagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity

public class Pagamento {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @NotBlank
    private String descricaoPagamento;

    @NotNull
    private BigDecimal valorPago;

    @NotNull
    private String dataPagamento;

    private String dataVencimento;

    @NotBlank
    private StatusPagamento status;

    private TipoPagamento tipoPag;


    @NotNull
    @ManyToMany
    List<Taxa> taxas;

}
