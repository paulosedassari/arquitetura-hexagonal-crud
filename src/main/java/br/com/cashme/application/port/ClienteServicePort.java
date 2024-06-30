package br.com.cashme.application.port;

import br.com.cashme.application.dto.ClienteDto;
import br.com.cashme.domain.model.Cliente;

import java.util.List;

public interface ClienteServicePort {

    void criarCliente(Cliente cliente);

    List<ClienteDto> buscarTodosClientes();

    ClienteDto buscarCliente(String nome);

    void atualizarCliente(String nome, Cliente cliente);

    void deletarCliente(String nome);
}
