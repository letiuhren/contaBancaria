package com.senai.contaBancaria.domain.repository;

import com.senai.contaBancaria.domain.entity.Gerente;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GerenteRepository extends JpaRepository <Gerente, String> {
    Optional<Gerente> findByEmail(String adminEmail);

    Optional<Gerente> findByCpfAndAtivoTrue(@NotNull(message = "CPF do gerente é obrigatório") String cpf);
}
