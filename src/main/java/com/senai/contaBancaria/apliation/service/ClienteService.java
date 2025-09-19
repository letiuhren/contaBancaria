package com.senai.contaBancaria.apliation.service;


import com.senai.contaBancaria.apliation.dto.ClienteRegistroDTO;
import com.senai.contaBancaria.apliation.dto.ClienteResponseDTO;
import com.senai.contaBancaria.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {


    private final ClienteRepository repository;

    public ClienteResponseDTO registrarClienteOuAnexarConta(ClienteRegistroDTO dto){

        var cliente = repository.findByCpfAndAtivoTrue(dto.cpf()).orElseGet(
                () -> repository.save(dto.toEntity)
        )

        return null;

    }
}
