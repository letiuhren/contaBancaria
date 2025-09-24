package com.senai.contaBancaria.interface_ui.controller;

import com.senai.contaBancaria.apliation.dto.ClienteRegistroDTO;
import com.senai.contaBancaria.apliation.dto.ClienteResponseDTO;
import com.senai.contaBancaria.apliation.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {


    private final ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> registrarCliente(@RequestBody ClienteRegistroDTO dto){
        ClienteResponseDTO novoCliente= service.registrarClienteOuAnexarConta(dto);
        return ResponseEntity.created(
                URI.create("/api/cliente/cpf"+ novoCliente.cpf())
        ).body(novoCliente);
    }
}
