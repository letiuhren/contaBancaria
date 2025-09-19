package com.senai.contaBancaria.interface_ui.controller;

import com.senai.contaBancaria.apliation.dto.ClienteRegistroDTO;
import com.senai.contaBancaria.apliation.dto.ClienteResponseDTO;
import com.senai.contaBancaria.apliation.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {


    private final ClienteService service;

    @PostMapping
    public ClienteResponseDTO registrarCliente(@RequestBody ClienteRegistroDTO dto){
        return service.registrarClienteOuAnexarConta(dto);
    }
}
