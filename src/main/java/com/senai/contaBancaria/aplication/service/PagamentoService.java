package com.senai.contaBancaria.aplication.service;

import com.senai.contaBancaria.aplication.dto.PagamentoRequestDTO;
import com.senai.contaBancaria.aplication.dto.PagamentoResponseDTO;
import com.senai.contaBancaria.aplication.dto.TaxaResponseDTO;
import com.senai.contaBancaria.domain.entity.Pagamento;
import com.senai.contaBancaria.domain.enums.StatusPagamento;
import com.senai.contaBancaria.domain.exceptions.PagamentoNaoEncontradoException;
import com.senai.contaBancaria.domain.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service //logica de negocio
@RequiredArgsConstructor // cria um construtor automatico
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository; // repository que interage com o banco de dados, vai salvar, buscar, listar e excluir pagamentos do banco
    private final TaxaService taxaService; //logica das taxas


    private BigDecimal calcularValorComTaxa(BigDecimal valorPago){
        List<TaxaResponseDTO> taxas = taxaService.listarTodasAsTaxas(); //pega todas as taxas ativas

        BigDecimal valorTotal = valorPago;


        for (TaxaResponseDTO taxa : taxas) {//coloca a taxa no valor

            valorTotal = valorTotal.add(valorTotal.multiply(taxa.percentual())); // coloca o percentual


            valorTotal = valorTotal.add(taxa.valorFixo()); // coloca o valor fixo da taxa
        }

        return valorTotal; //retorna o valor com as taxas

    }


    public PagamentoResponseDTO criarPagamento(PagamentoRequestDTO request) { //metodo que o controller vai chamar, recebe a requisiçaõ do cliente e responde
        Pagamento pagamento = new Pagamento();// assim cria a entidade


        pagamento.setConta(request.conta());
        pagamento.setDescricaoPagamento(request.descricaoPagamento());
        pagamento.setValorPago(new BigDecimal(request.valorPago())); //converte de string para big decimal
        pagamento.setDataPagamento(LocalDateTime.parse(request.dataVencimento())); //converte string para localdatetime
        pagamento.setStatus(StatusPagamento.PENDENTE); //define o pagamento como pendente primeiro


        Pagamento salvo = pagamentoRepository.save(pagamento); //salva no banco


        return new PagamentoResponseDTO(
                salvo.getId(),
                salvo.getConta(),
                salvo.getDescricaoPagamento(),
                salvo.getValorPago().toString(),
                salvo.getDataPagamento().toString()
        ); // converte a entidade para um dto de resposta, para nao expor detalhes da entidade e dar ao cliente apenas os dados necessario
    }


    public List<PagamentoResponseDTO> listarPagamentos() {
        return pagamentoRepository.findAll().stream()
                .map(p -> new PagamentoResponseDTO(
                        p.getId(),
                        p.getConta(),
                        p.getDescricaoPagamento(),
                        p.getValorPago().toString(),
                        p.getDataPagamento().toString()
                ))
                .toList();
    }// busca os pagamentos, converte em DTO, e retorna uma lista


    public PagamentoResponseDTO buscarPagamentoPorId(String id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new PagamentoNaoEncontradoException(id.toString())); //busca o pagamento

        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getConta(),
                pagamento.getDescricaoPagamento(),
                pagamento.getValorPago().toString(),
                pagamento.getDataPagamento().toString()
        );
    }


    public void deletarPagamento(String id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new PagamentoNaoEncontradoException(id.toString());
        }
        pagamentoRepository.deleteById(id); //verifica se o pagamento existe
    }
}