package com.senai.contaBancaria.domain.repository;

import com.senai.contaBancaria.domain.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, String> {
}
