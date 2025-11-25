package com.senai.contaBancaria.aplication.service;

import com.senai.contaBancaria.aplication.dto.TaxaDTO;
import com.senai.contaBancaria.aplication.dto.TaxaResponseDTO;
import com.senai.contaBancaria.domain.entity.Taxa;
import com.senai.contaBancaria.domain.exceptions.EntidadeNaoEncontradaException;
import com.senai.contaBancaria.domain.repository.TaxaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

@RequiredArgsConstructor

public class TaxaService {

    private final TaxaRepository repository;

    public TaxaResponseDTO registrarTaxa(TaxaDTO dto) {

        var taxa = dto.toEntity();

        var taxaSalva = repository.save(taxa);

        return TaxaResponseDTO.fromEntity(taxaSalva);

    }

    // --- LEITURA (LISTAGEM) ---

    public List<TaxaResponseDTO> listarTodas() {

        return repository.findAll().stream()

                .map(TaxaResponseDTO::fromEntity)

                .toList();

    }


    public TaxaResponseDTO buscarPorId(String id) {

        var taxa = buscarTaxaPorId(id);

        // 🚨 Usa o método fromEntity() para mapeamento

        return TaxaResponseDTO.fromEntity(taxa);

    }

    public TaxaResponseDTO atualizarTaxa(String id, TaxaDTO dto) {

        var taxa = buscarTaxaPorId(id);

        taxa.setDescricao(dto.descricao());

        taxa.setPercentual(dto.percentual());

        taxa.setValorFixo(dto.valorFixo());

        var taxaSalva = repository.save(taxa);


        return TaxaResponseDTO.fromEntity(taxaSalva);

    }


    public void deletarTaxa(String id) {

        var taxa = buscarTaxaPorId(id);

        repository.delete(taxa);

    }


    private Taxa buscarTaxaPorId(String id) {

        return repository.findById(id).orElseThrow(

                () -> new EntidadeNaoEncontradaException("Taxa com ID " + id)

        );

    }

}
