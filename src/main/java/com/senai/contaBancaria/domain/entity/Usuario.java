package com.senai.contaBancaria.domain.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column (nullable = false, length = 11)
    private String cpf;


    }
