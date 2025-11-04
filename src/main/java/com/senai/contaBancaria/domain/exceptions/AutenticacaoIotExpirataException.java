package com.senai.contaBancaria.domain.exceptions;

public class AutenticacaoIotExpirataException extends RuntimeException {
    public AutenticacaoIotExpirataException(String message) {
        super(message);
    }
}
