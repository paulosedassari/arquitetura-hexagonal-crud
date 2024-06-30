package br.com.cashme.application.port;

import br.com.cashme.domain.model.Cliente;

import java.util.List;

public interface ClienteServicePort {

    Cliente criarCliente(Cliente cliente);

    List<Cliente> buscarTodosClientes();

    Cliente buscarCliente(String nome);

    Cliente atualizarCliente(String nome, Cliente cliente);

    void deletarCliente(String nome);
}
