package br.com.cashme.domain.repository;

import br.com.cashme.adapter.outbound.entity.ClienteEntity;
import br.com.cashme.adapter.outbound.repositories.ClienteRepositoryJpa;
import br.com.cashme.application.port.ClienteRepositoryPort;
import br.com.cashme.domain.model.Cliente;

import java.util.List;

public class ClienteRepository implements ClienteRepositoryPort {

    private final ClienteRepositoryJpa clienteRepositoryJpa;

    public ClienteRepository(ClienteRepositoryJpa clienteRepositoryJpa) {
        this.clienteRepositoryJpa = clienteRepositoryJpa;
    }

    @Override
    public void salvar(Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity(cliente);
        clienteRepositoryJpa.save(clienteEntity);
    }

    @Override
    public List<Cliente> buscarTodos() {
        List<ClienteEntity> clientesEntity = clienteRepositoryJpa.findAll();
        return clientesEntity.stream().map(Cliente::new).toList();
    }

    @Override
    public Cliente buscar(String nome) {
        ClienteEntity clienteEntity = clienteRepositoryJpa.findByNome(nome);
        return new Cliente(clienteEntity);
    }

    @Override
    public void atualizar(Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity(cliente);
        clienteRepositoryJpa.save(clienteEntity);
    }

    @Override
    public void deletar(String nome) {
        clienteRepositoryJpa.deleteByNome(nome);
    }
}
