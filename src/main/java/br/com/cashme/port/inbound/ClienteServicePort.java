package br.com.cashme.port.inbound;

import br.com.cashme.adapter.dto.ClienteDto;

import java.util.List;

public interface ClienteServicePort {

    void criarCliente(ClienteDto produtoDto);

    List<ClienteDto> buscarTodosClientes();

    ClienteDto buscarCliente();

    ClienteDto atualizarCliente(String nome, ClienteDto produtoDto);

    void deletarCliente(String nome);
}
