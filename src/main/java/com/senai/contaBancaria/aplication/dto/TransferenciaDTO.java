package com.senai.contaBancaria.aplication.dto;

import java.math.BigDecimal;

public record TransferenciaDTO(
        BigDecimal valor,
        String contaDestino
) {
}
