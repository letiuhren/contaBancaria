package com.senai.contaBancaria.domain.repository;

import com.senai.contaBancaria.domain.entity.Taxa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxaRepository extends JpaRepository<Taxa, String> {
}
