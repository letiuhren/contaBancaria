package com.senai.contaBancaria.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(
        name = "cliente"
)

public class Cliente extends Usuario {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    //cascade para criar o relacionamnto entre cliente e conta, criando simultaneamente. Além de, quando apagar o cliente, apagar todas as contas relacionadas.
    private List<Conta> contas;

}
