package br.com.cashme.application.port;

import br.com.cashme.domain.model.Cliente;

import java.util.List;

public interface ClienteRepositoryPort {

    Cliente salvar(Cliente cliente);

    List<Cliente> buscarTodos();

    Cliente buscar(String nome);

    void deletar(String nome);
}
