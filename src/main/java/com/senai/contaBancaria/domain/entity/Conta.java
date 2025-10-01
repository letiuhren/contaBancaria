package com.senai.contaBancaria.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING, length = 20)
@Table (name = "conta",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_conta_numero", columnNames = "numero"),
        @UniqueConstraint(name = "uk_cliente_tipo", columnNames = {"cliente_id", "tipo_conta"})
    }
)
@SuperBuilder
@NoArgsConstructor
public abstract class Conta{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;// classe repper é o que representa, que são classes que podem ser nulas ou não
    @Column (nullable = false, length = 20)
    private String numero;
    @Column (nullable = false, precision = 20, scale = 2)
    private BigDecimal saldo;
    @Column (nullable = false)
    private boolean ativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "cliente_id", foreignKey = @ForeignKey(name="fk_conta_cliente"))
    private Cliente cliente;

    public abstract String  getTipo();

    public void sacar (BigDecimal valor){
        validarValorMaiorQueZero(valor, BigDecimal.ZERO, "Valor de saque inválido.");
        validarValorMaiorQueZero (this.saldo (valor));
        this.saldo = this.saldo.subtract(valor);
    }

    public void depositar(BigDecimal valor) {
        validarValorMaiorQueZero(valor, BigDecimal.ZERO, "Valor de depósito inválido.");
        this.saldo = this.saldo.add(valor);
    }

    private static void validarValorMaiorQueZero(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Valor deve ser maior que zero.");
        }
    }

    public void tranferir(BigDecimal valor, Conta contaDestino){
        if (this.equals(contaDestino.getId())){
            throw new IllegalArgumentException("Não é possível transferir para a mesma conta.");
        }
        this.sacar(valor);
        contaDestino.depositar(valor);

    }
}
