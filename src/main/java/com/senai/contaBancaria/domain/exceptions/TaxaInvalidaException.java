package com.senai.contaBancaria.domain.exceptions;

public class TaxaInvalidaException extends RuntimeException {
    public TaxaInvalidaException() {

        super("A taxa informada é inválida");
    }
}