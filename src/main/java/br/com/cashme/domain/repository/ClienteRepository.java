package br.com.cashme.domain.repository;

import br.com.cashme.adapter.outbound.entity.ClienteEntity;
import br.com.cashme.adapter.outbound.repositories.ClienteRepositoryJpa;
import br.com.cashme.application.port.ClienteRepositoryPort;
import br.com.cashme.common.ClienteMapper;
import br.com.cashme.domain.model.Cliente;

import java.util.List;
import java.util.Objects;

public class ClienteRepository implements ClienteRepositoryPort {

    private final ClienteRepositoryJpa clienteRepositoryJpa;
    private final ClienteMapper clienteMapper;

    public ClienteRepository(ClienteRepositoryJpa clienteRepositoryJpa, ClienteMapper clienteMapper) {
        this.clienteRepositoryJpa = clienteRepositoryJpa;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity clienteCriado = clienteRepositoryJpa.save(clienteMapper.toEntity(cliente));
        return clienteMapper.toModel(clienteCriado);
    }

    @Override
    public List<Cliente> buscarTodos() {
        List<ClienteEntity> clientesEntity = clienteRepositoryJpa.findAll();
        return clientesEntity.stream().map(clienteMapper::toModel).toList();
    }

    @Override
    public Cliente buscar(String nome) {
        ClienteEntity clienteEntity = clienteRepositoryJpa.findByNome(nome);
        if (Objects.isNull(clienteEntity)) return new Cliente();

        return clienteMapper.toModel(clienteEntity);
    }

    @Override
    public void deletar(String nome) {
        clienteRepositoryJpa.deleteByNome(nome);
    }
}
