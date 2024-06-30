package br.com.cashme.domain.service;

import br.com.cashme.application.port.ClienteRepositoryPort;
import br.com.cashme.application.port.ClienteServicePort;
import br.com.cashme.common.ClienteMapper;
import br.com.cashme.common.Constants;
import br.com.cashme.common.exception.RegistroNaoEncontradoException;
import br.com.cashme.domain.model.Cliente;

import java.util.List;
import java.util.Objects;

public class ClienteService implements ClienteServicePort {

    private final ClienteRepositoryPort clienteRepositoryPort;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepositoryPort clienteRepositoryPort, ClienteMapper clienteMapper) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public Cliente criarCliente(Cliente cliente) {
        return clienteRepositoryPort.salvar(cliente);
    }

    @Override
    public List<Cliente> buscarTodosClientes() {
        return clienteRepositoryPort.buscarTodos();
    }

    @Override
    public Cliente buscarCliente(String nome) {
        return clienteRepositoryPort.buscar(nome);
    }

    @Override
    public Cliente atualizarCliente(String nome, Cliente alteracoesCliente) {
        Cliente cliente = verificarExistenciaDoCliente(nome);
        clienteMapper.updateCliente(alteracoesCliente, cliente);

        return clienteRepositoryPort.salvar(cliente);
    }

    @Override
    public void deletarCliente(String nome) {
        verificarExistenciaDoCliente(nome);
        clienteRepositoryPort.deletar(nome);
    }

    private Cliente verificarExistenciaDoCliente(String nome) {
        Cliente cliente = clienteRepositoryPort.buscar(nome);

        if (Objects.isNull(cliente.getId()))
            throw new RegistroNaoEncontradoException(Constants.CLIENTE_NAO_ENCONTRADO_PARA_REALIZAR_A_OPERACAO);

        return cliente;
    }
}
