package com.senai.contaBancaria.domain.repository;

import com.senai.contaBancaria.domain.entity.Gerente;

import java.util.Optional;

public interface GerenteRepository {
    Optional<Object> findByEmail(String adminEmail);

    void save(Gerente admin);
}
