package com.senai.contaBancaria.domain.repository;

import com.senai.contaBancaria.domain.entity.Taxa;
import com.senai.contaBancaria.domain.enums.TipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaxaRepository extends JpaRepository<Taxa, String> {
    public List<Optional<Taxa>> findByTipoPag(TipoPagamento tipoPagamento);
}
