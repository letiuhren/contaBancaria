package com.senai.contaBancaria.domain.repository;

import java.lang.ScopedValue;

public interface UsuarioRepository {
    <T> ScopedValue<T> findByEmail(String email);
}
