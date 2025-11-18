package com.senai.contaBancaria.aplication.service;

import com.senai.contaBancaria.domain.entity.Conta;
import com.senai.contaBancaria.domain.entity.Pagamento;
import com.senai.contaBancaria.domain.enums.StatusPagamento;
import com.senai.contaBancaria.domain.repository.ContaRepository;
import com.senai.contaBancaria.domain.repository.PagamentoRepository;
import com.senai.contaBancaria.domain.service.PagamentoDomainService;

import java.math.BigDecimal;

public class PagamentoAppService {
    private final ContaRepository contaRepository;
    private final PagamentoDomainService domainService;
    private final PagamentoRepository pagamentoRepository;

    public PagamentoAppService(ContaRepository contaRepository,
                               PagamentoDomainService domainService,
                               PagamentoRepository pagamentoRepository) {
        this.contaRepository = contaRepository;
        this.domainService = domainService;
        this.pagamentoRepository = pagamentoRepository;
    }

    public String efetuarPagamento(Pagamento pagamento) {
        // Busca conta vinculada
        Conta conta = contaRepository.findById(pagamento.getConta().getId())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        // Calcula o valor total com taxas
        BigDecimal total = domainService.calcularValorTotal(pagamento);

        // Valida o saldo da conta
        domainService.validarSaldo(conta, total);

        // Atualiza saldo e salva no banco
        conta.setSaldo(conta.getSaldo().subtract(total));
        contaRepository.save(conta);


        pagamento.setStatus(StatusPagamento.APROVADO);
        pagamentoRepository.save(pagamento);

        // Retorna mensagem de confirmação
        return "Pagamento realizado com sucesso! Total debitado: R$ " + total;
    }
}
