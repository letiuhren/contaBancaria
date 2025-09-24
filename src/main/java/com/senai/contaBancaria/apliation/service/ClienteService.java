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
                () -> repository.save(dto.toEntity())
        );

        var contas = cliente.getContas();
        var novaConta = dto.contaDTO().toEntity(cliente);

        boolean jaTemTipo = contas.stream()
                .anyMatch(c -> c.getClass().equals(novaConta.getClass()) && c.isAtiva());

        if(jaTemTipo)
            throw new RuntimeException("Cliente já possui uma conta ativa desse tipo");

        cliente.getContas().add(novaConta);

        return ClienteResponseDTO.fromEntity(repository.save(cliente));

    }
}
