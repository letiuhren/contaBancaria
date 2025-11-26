package com.senai.contaBancaria.domain.exceptions;

public class TaxaInvalidaException extends RuntimeException {
    public TaxaInvalidaException(String descricao) {

        super("A taxa informada é inválida: " + descricao);
    }
}