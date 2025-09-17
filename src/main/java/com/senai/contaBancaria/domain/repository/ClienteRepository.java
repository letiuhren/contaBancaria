package com.senai.contaBancaria.domain.repository;

import com.senai.contaBancaria.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}