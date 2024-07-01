package br.com.cashme.domain.service;

import br.com.cashme.application.port.ClienteRepositoryPort;
import br.com.cashme.application.port.ClienteServicePort;
import br.com.cashme.common.ClienteMapper;
import br.com.cashme.common.Constants;
import br.com.cashme.common.exception.CashMeException;
import br.com.cashme.common.exception.RegistroNaoEncontradoException;
import br.com.cashme.domain.model.Cliente;

import java.util.List;

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
    public List<Cliente> buscarCliente(String nome) {
        return clienteRepositoryPort.buscar(nome);
    }

    @Override
    public Cliente atualizarCliente(String nome, Cliente alteracoesCliente) {
        List<Cliente> clientes = verificarExistenciaDoCliente(nome);
        if (clientes.size() > 1) throw new CashMeException("Não foi possível realizar a atualização. Existe mais de um cliente com este nome.");
        clienteMapper.updateCliente(alteracoesCliente, clientes.get(0));

        return clienteRepositoryPort.salvar(clientes.get(0));
    }

    @Override
    public void deletarCliente(String nome) {
        verificarExistenciaDoCliente(nome);
        clienteRepositoryPort.deletar(nome);
    }

    private List<Cliente> verificarExistenciaDoCliente(String nome) {
        List<Cliente> clientes = clienteRepositoryPort.buscar(nome);

        if (clientes.isEmpty())
            throw new RegistroNaoEncontradoException(Constants.CLIENTE_NAO_ENCONTRADO_PARA_REALIZAR_A_OPERACAO);

        return clientes;
    }
}
