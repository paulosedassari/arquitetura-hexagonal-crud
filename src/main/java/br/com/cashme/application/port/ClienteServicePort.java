package br.com.cashme.application.port;

import br.com.cashme.application.dto.ClienteDto;

import java.util.List;

public interface ClienteServicePort {

    void criarCliente(ClienteDto produtoDto);

    List<ClienteDto> buscarTodosClientes();

    ClienteDto buscarCliente(String nome);

    void atualizarCliente(String nome, ClienteDto produtoDto);

    void deletarCliente(String nome);
}
