package com.senai.contaBancaria.interface_ui.controller;

import com.senai.contaBancaria.aplication.dto.ContaAtualizacaoDTO;
import com.senai.contaBancaria.aplication.dto.ContaResumoDTO;
import com.senai.contaBancaria.aplication.dto.TransferenciaDTO;
import com.senai.contaBancaria.aplication.dto.ValorSaqueDepositoDTO;
import com.senai.contaBancaria.aplication.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Contas", description = "Gerenciamento de contas do banco")

@RestController
@RequestMapping("/api/conta")
@RequiredArgsConstructor
public class ContaController {

    private final ContaService service;

    @Operation(
            summary = "Listar todas as contas",
            description = "Retorna todos as contas cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
            }
    )
    @GetMapping
    public ResponseEntity<List<ContaResumoDTO>> listarTodasContas() {
        return ResponseEntity.ok(service.listarTodasContas());
    }

    @Operation(
            summary = "Buscar conta por número",
            description = "Retorna uma conta existente a partir do seu número",
            parameters = {
                    @Parameter(name = "número", description = "número da conta  a ser buscada", example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Conta encontrado"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Conta não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "\"COnta com número 99 não encontrada.\"")
                            )
                    )
            }
    )
    @GetMapping("/{numeroDaConta}")
    public ResponseEntity<ContaResumoDTO> buscarContaPorNumero(@PathVariable String numeroDaConta) {
        return ResponseEntity.ok(service.buscarContaPorNumero(numeroDaConta));
    }

    @Operation(
            summary = "Atualizar uma conta",
            description = "Atualiza os dados de uma conta existente com novas informações",
            parameters = {
                    @Parameter(name = "id", description = "Número da conta a ser atualizada", example = "1")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ContaAtualizacaoDTO.class),
                            examples = @ExampleObject(name = "Exemplo de atualização", value = """
                        {
                      ,
                          "numero": "123456",
                          "ativo": "true"
                        }
                    """)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Conta atualizada com sucesso"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "Conta Inválida", value = "\"Dados inválidos\""),
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Conta não encontrada",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "\"Conta com número 99 não encontrado.\"")
                            )
                    )
            }
    )
    @PutMapping("/{numeroDaConta}")
    public ResponseEntity<ContaResumoDTO> atualizarConta(@PathVariable String numeroDaConta,
                                                         @RequestBody ContaAtualizacaoDTO dto) {
        return ResponseEntity.ok(service.atualizarConta(numeroDaConta,dto));
    }

    @Operation(
            summary = "Deletar uma conta",
            description = "Remove uma conta da base de dados a partir do seu número",
            parameters = {
                    @Parameter(name = "número", description = "número da conta a ser deletada", example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Conta removida com sucesso"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Conta não encontrada",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "\"Conta com número 99 não encontrado.\"")
                            )
                    )
            }
    )
    @DeleteMapping("/{numeroDaConta}")
    public ResponseEntity<Void> deletarConta(@PathVariable String numeroDaConta) {
        service.
                deletarConta(numeroDaConta);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Sacar valor de uma conta",
            description = "Realiza o saque de um valor na conta especificada pelo número",
            parameters = {
                    @Parameter(name = "numeroDaConta", description = "Número da conta para o saque", example = "123456")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ValorSaqueDepositoDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de saque",
                                    value = """
                                        {
                                          "valor": 200.00
                                        }
                                    """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Saque realizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Saldo insuficiente ou valor inválido"),
                    @ApiResponse(responseCode = "404", description = "Conta não encontrada")
            }
    )
    @PostMapping("/{numeroDaConta}/sacar")
    public ResponseEntity<ContaResumoDTO> sacar(@PathVariable String numeroDaConta,
                                                @Valid @RequestBody ValorSaqueDepositoDTO dto) {
        return ResponseEntity.ok(service.sacar(numeroDaConta, dto));
    }


    @Operation(
            summary = "Depositar valor em uma conta",
            description = "Realiza um depósito em uma conta a partir do número da conta",
            parameters = {
                    @Parameter(name = "numeroDaConta", description = "Número da conta para o depósito", example = "123456")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ValorSaqueDepositoDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de depósito",
                                    value = """
                                        {
                                          "valor": 500.00
                                        }
                                    """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Depósito realizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Valor inválido"),
                    @ApiResponse(responseCode = "404", description = "Conta não encontrada")
            }
    )
    @PostMapping("/{numeroDaConta}/depositar")
    public ResponseEntity<ContaResumoDTO> depositar(@PathVariable String numeroDaConta,
                                                    @Valid @RequestBody ValorSaqueDepositoDTO valor) {
        return ResponseEntity.ok(service.depositar(numeroDaConta, valor));
    }

    @Operation(
            summary = "Transferir valor para outra conta",
            description = "Realiza uma transferência entre contas do banco",
            parameters = {
                    @Parameter(name = "numeroDaConta", description = "Número da conta de origem", example = "123456")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = TransferenciaDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de transferência",
                                    value = """
                                        {
                                          "contaDestino": "654321",
                                          "valor": 150.00
                                        }
                                    """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transferência realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Saldo insuficiente ou valor inválido"),
                    @ApiResponse(responseCode = "404", description = "Conta de origem ou destino não encontrada")
            }
    )
    @PostMapping("/{numeroDaConta}/transferir")
    public ResponseEntity<ContaResumoDTO> transferir(@PathVariable String numeroDaConta,
                                                     @Valid @RequestBody TransferenciaDTO dto) {
        return ResponseEntity.ok(service.transferir(numeroDaConta, dto));
    }

    @PostMapping("/{numeroDaConta}/rendimento")
    public ResponseEntity<ContaResumoDTO> aplicarRendimento(@PathVariable String numeroDaConta) {
        return ResponseEntity.ok(service.aplicarRendimento(numeroDaConta));
    }


}
