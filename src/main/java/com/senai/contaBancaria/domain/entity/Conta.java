package com.senai.contaBancaria.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@MappedSuperclass
@Entity
@Data
public class Conta{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String numero;

    private int saldo;

    @OneToOne(mappedBy = "cliente")
    private List<Cliente> cliente;

}
