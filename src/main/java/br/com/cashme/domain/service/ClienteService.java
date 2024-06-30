package br.com.cashme.domain.service;

import br.com.cashme.application.dto.ClienteDto;
import br.com.cashme.application.port.ClienteRepositoryPort;
import br.com.cashme.application.port.ClienteServicePort;
import br.com.cashme.common.Constants;
import br.com.cashme.common.exception.RegistroNaoEncontradoException;
import br.com.cashme.domain.model.Cliente;

import java.util.List;
import java.util.Objects;

public class ClienteService implements ClienteServicePort {

    private final ClienteRepositoryPort clienteRepositoryPort;

    public ClienteService(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    public void criarCliente(Cliente cliente) {
        clienteRepositoryPort.salvar(cliente);
    }

    @Override
    public List<ClienteDto> buscarTodosClientes() {
        List<Cliente> clientes = clienteRepositoryPort.buscarTodos();
        return clientes.stream().map(ClienteDto::new).toList();
    }

    @Override
    public ClienteDto buscarCliente(String nome) {
        Cliente cliente = clienteRepositoryPort.buscar(nome);
        return new ClienteDto(cliente);
    }

    @Override
    public void atualizarCliente(String nome, Cliente alteracoesCliente) {
        Cliente cliente = verificarExistenciaDoCliente(nome);
        cliente.setNome(alteracoesCliente.getNome());
        cliente.getEndereco().setRua(alteracoesCliente.getEndereco().getRua());
        cliente.getEndereco().setNumero(alteracoesCliente.getEndereco().getNumero());
        cliente.getEndereco().setCep(alteracoesCliente.getEndereco().getCep());
        cliente.getEndereco().setBairro(alteracoesCliente.getEndereco().getBairro());
        cliente.getEndereco().setCidade(alteracoesCliente.getEndereco().getCidade());
        cliente.getEndereco().setEstado(alteracoesCliente.getEndereco().getEstado());
        clienteRepositoryPort.salvar(cliente);
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
