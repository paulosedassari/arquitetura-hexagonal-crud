package br.com.cashme.domain.repository;

import br.com.cashme.adapter.outbound.entity.ClienteEntity;
import br.com.cashme.adapter.outbound.repositories.ClienteRepositoryJpa;
import br.com.cashme.common.ClienteMapper;
import br.com.cashme.domain.model.Cliente;
import br.com.cashme.domain.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ClienteRepositoryTest {

    private static final String NOME_CLIENTE = "John Doe";

    ClienteRepositoryJpa clienteRepositoryJpa;
    ClienteMapper clienteMapper;
    ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        clienteRepositoryJpa = mock(ClienteRepositoryJpa.class);
        clienteMapper = mock(ClienteMapper.class);
        clienteRepository = new ClienteRepository(clienteRepositoryJpa, clienteMapper);
    }

    @Test
    void testShouldSaveValidClienteAndReturnSavedCliente() {
        Cliente cliente = getCliente();
        ClienteEntity clienteEntity = getClienteEntity();

        when(clienteMapper.toEntity(cliente)).thenReturn(clienteEntity);
        when(clienteRepositoryJpa.save(clienteEntity)).thenReturn(clienteEntity);
        when(clienteMapper.toModel(clienteEntity)).thenReturn(cliente);

        Cliente result = clienteRepository.salvar(cliente);

        assertNotNull(result);
        assertEquals(NOME_CLIENTE, result.getNome());
        verify(clienteRepositoryJpa, times(1)).save(clienteEntity);
    }

    @Test
    void testReturnsListOfClienteObjectsWhenMultipleClienteentityRecordsExist() {
        List<ClienteEntity> clienteEntities = List.of(
                new ClienteEntity(),
                new ClienteEntity()
        );

        when(clienteRepositoryJpa.findAll()).thenReturn(clienteEntities);
        when(clienteMapper.toModel(any(ClienteEntity.class))).thenReturn(new Cliente());

        List<Cliente> clientes = clienteRepository.buscarTodos();

        assertNotNull(clientes);
        assertEquals(2, clientes.size());
    }

    @Test
    void testReturnsListOfClienteWhenValidNameIsProvided() {
        List<ClienteEntity> clienteEntities = new ArrayList<>();
        ClienteEntity clienteEntity = getClienteEntity();
        clienteEntities.add(clienteEntity);

        when(clienteRepositoryJpa.findByNome(NOME_CLIENTE)).thenReturn(clienteEntities);
        when(clienteMapper.toModel(clienteEntity)).thenReturn(new Cliente());

        List<Cliente> clientes = clienteRepository.buscar(NOME_CLIENTE);

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
    }

    @Test
    void testDeleteClientByNameWhenExists() {
        doNothing().when(clienteRepositoryJpa).deleteByNome(NOME_CLIENTE);

        clienteRepository.deletar(NOME_CLIENTE);

        verify(clienteRepositoryJpa, times(1)).deleteByNome(NOME_CLIENTE);
    }

    private static Cliente getCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome(NOME_CLIENTE);
        cliente.setEndereco(new Endereco());
        return cliente;
    }

    private static ClienteEntity getClienteEntity() {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNome(NOME_CLIENTE);
        return clienteEntity;
    }
}