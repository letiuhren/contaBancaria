package com.senai.contaBancaria.interface_ui.controller;

import com.senai.contaBancaria.aplication.dto.ClienteRegistroDTO;
import com.senai.contaBancaria.aplication.dto.TaxaDTO;
import com.senai.contaBancaria.aplication.dto.TaxaResponseDTO;
import com.senai.contaBancaria.aplication.service.TaxaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

public class TaxaController {

    private final TaxaService service;

    @Operation(
            summary = "Cadastrar uma nova taxa",
            description = "Adiciona uma nova taxa ao banco de dados após todas as validações",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ClienteRegistroDTO.class),
                            examples = @ExampleObject(name = "Exemplo válido", value = """
                                        {
                                          "id": 1,
                                          "descricao" : "taxaBoleto",
                                          "Percentual" : 0.5,
                                          "valor fixo" : 3.00
                                        }
                                    """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Taxa cadastrada com sucesso"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "Taxa Inválida", value = "\"Dados inválidos\""),
                                    }
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<TaxaResponseDTO> registrarCliente(@RequestBody TaxaDTO dto){
        TaxaResponseDTO novaTaxa= service.registrarTaxa(dto);
        return ResponseEntity.created(
                URI.create("/api/taxa/id"+ novaTaxa.id())
        ).body(novaTaxa);
    }

    @Operation(
            summary = "Listar todas as taxas",
            description = "Retorna todas as taxas cadastradas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
            }
    )
    @GetMapping
    public ResponseEntity<List<TaxaResponseDTO>> listarTodasAsTaxas(){
        return ResponseEntity.ok(service.listarTodasAsTaxas());
    }

    @Operation(
            summary = "Buscar taxa por ID",
            description = "Retorna uma taxa existente a partir do seu id",
            parameters = {
                    @Parameter(name = "id", description = "CPF do cliente a ser buscado", example = "123.123.123-00")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "\"Cliente com cpf 000.000.00-000 não encontrado.\"")
                            )
                    )
            }
    )
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<TaxaResponseDTO> buscarClienteAtivoPorCpf(@PathVariable String cpf){
        return ResponseEntity.ok(service.buscarClienteAtivoPorCpf(cpf));
    }

    @Operation(
            summary = "Atualizar um cliente",
            description = "Atualiza os dados de um cliente existente com novas informações",
            parameters = {
                    @Parameter(name = "CPF", description = "CPF do cliente a ser atualizado", example = "123.123.123-00")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ClienteRegistroDTO.class),
                            examples = @ExampleObject(name = "Exemplo de atualização", value = """
                        {
                          "id": 1,
                          "nome": "João Silva",
                          "cpf": "123.456.789-00",
                          "email": josilva@gmail.com",
                          "senha": "novaSenhaSegura123"
                        }
                    """)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "Cliente inválido", value = "\"Dados inválidos\""),
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "\"Cliente com CPF 000.000.000-0 não encontrado.\"")
                            )
                    )
            }
    )
    @PutMapping("/cpf/{cpf}")
    public ResponseEntity<TaxaResponseDTO> atualizarCliente(@PathVariable String cpf,
                                                               @RequestBody ClienteRegistroDTO dto) {
        return ResponseEntity.ok(service.atualizarCliente(cpf, dto));
    }

    @Operation(
            summary = "Deletar um cliente",
            description = "Remove um cliente da base de dados a partir do seu CPF",
            parameters = {
                    @Parameter(name = "CPF", description = "CPF do cliente a ser deletado", example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "cliente removido com sucesso"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "\"Cliente com CPF 000.000.000-00 não encontrado.\"")
                            )
                    )
            }
    )
    @DeleteMapping("/cpf/{cpf}")
    public ResponseEntity<Void> deletarCliente(@PathVariable String cpf){
        service.deletarCliente(cpf);
        return ResponseEntity.noContent().build();
    }
}



}
