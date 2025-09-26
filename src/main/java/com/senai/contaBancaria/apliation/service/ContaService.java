package com.senai.contaBancaria.apliation.service;

import com.senai.contaBancaria.apliation.dto.ClienteResponseDTO;
import com.senai.contaBancaria.apliation.dto.ContaResumoDTO;
import com.senai.contaBancaria.domain.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class ContaService {
        private final ContaRepository repository;

        @Transactional(readOnly = true)
        public List<ContaResumoDTO> listarTodasContas() {
            return repository.findAllByAtivaTrue().stream()
                    .map(ContaResumoDTO::fromEntity).toList();
        }

        @Transactional(readOnly = true)
        public ContaResumoDTO buscarContaPorNumero(String numero) {
            return ContaResumoDTO.fromEntity(
                    repository.findByNumeroAndAtivaTrue(numero)
                            .orElseThrow(() -> new RuntimeException("Conta não encontrada"))
            );
        }

    public ContaResumoDTO atualizarConta(String numeroDaConta, ContaResumoDTO dto) {
        var conta =  repository.findByNumeroAndAtivaTrue(numeroDaConta).orElseThrow(
                            ()-> new RuntimeException("Conta não encontrada.")
                    );
        return ContaResumoDTO.fromEntity(repository.save(conta));
            
    }
}
