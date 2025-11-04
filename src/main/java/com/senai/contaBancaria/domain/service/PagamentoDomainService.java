package com.senai.contaBancaria.domain.service;

import com.senai.contaBancaria.domain.entity.Conta;
import com.senai.contaBancaria.domain.entity.Pagamento;
import com.senai.contaBancaria.domain.entity.Taxa;
import com.senai.contaBancaria.domain.exceptions.SaldoInsuficienteException;
import com.senai.contaBancaria.domain.repository.ContaRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class PagamentoDomainService {
    public BigDecimal calcularValorTotal(Pagamento pagamento) {
        BigDecimal total = pagamento.getValorPago();

        List<Taxa> taxas = pagamento.getTaxas() == null ? Collections.emptyList() : pagamento.getTaxas();

        for (Taxa taxa : taxas) {
            BigDecimal percentual = taxa.getPercentual() != null ? taxa.getPercentual() : BigDecimal.ZERO;

            BigDecimal valorTaxa = pagamento.getValorPago()
                    .multiply(percentual);

            total = total.add(valorTaxa);
        }

        return total;
    }

    public void validarSaldo(Conta conta, BigDecimal total) {
        if (conta.getSaldo().compareTo(total) < 0) {
            throw new SaldoInsuficienteException();
        }
    }

}
