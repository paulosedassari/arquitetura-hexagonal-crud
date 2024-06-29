//package br.com.cashme.adapter;
//
//import br.com.cashme.application.dto.ClienteDto;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Tag(name = "Clientes", description = "Serviço responsável pelas operações do Cliente.")
//public interface ClienteSwagger {
//
//    @Operation(
//            summary = "Criar Cliente",
//            description = "Endpoint para criar um Cliente."
//    )
//    @PostMapping
//    void criarCliente(@RequestBody ClienteDto clienteDto);
//
//    @Operation(
//            summary = "Buscar Clientes",
//            description = "Recuperar todos os clientes."
//    )
//    @GetMapping
//    ResponseEntity<List<ClienteDto>> buscarTodos();
//
//    @Operation(
//            summary = "Buscar Cliente.",
//            description = "Recuperar um cliente pelo nome fornecido."
//    )
//    @GetMapping("{nome}")
//    ResponseEntity<ClienteDto> buscarCliente(@PathVariable("nome") String nome);
//
//    @Operation(
//            summary = "Atualizar um cliente.",
//            description = "Endpoint para atualizar um cliente."
//    )
//    @PutMapping("{nome}")
//    void atualizarCliente(@PathVariable("nome") String nome, @RequestBody ClienteDto clienteDto);
//
//    @Operation(
//            summary = "Deletar um cliente.",
//            description = "Endpoint para deletar um cliente."
//    )
//    @DeleteMapping("{nome}")
//    void deletarCliente(String nome);
//}
