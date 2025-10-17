package com.senai.contaBancaria.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

@Table(
        name = "gerente",
        uniqueConstraints = @UniqueConstraint(name="uk_gerente_cpf", columnNames = "cpf")
)
public class Gerente extends Usuario {

    public static Object builder() {
        return null;
    }
}
