package br.com.cashme.domain.service;

import br.com.cashme.application.port.ClienteRepositoryPort;
import br.com.cashme.common.ClienteMapper;
import br.com.cashme.domain.model.Cliente;
import br.com.cashme.domain.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    private static final String NOME_CLIENTE = "John Doe";

    ClienteRepositoryPort clienteRepositoryPort;
    ClienteMapper clienteMapper;
    ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        clienteRepositoryPort = mock(ClienteRepositoryPort.class);
        clienteMapper = mock(ClienteMapper.class);
        clienteService = new ClienteService(clienteRepositoryPort, clienteMapper);
    }

    @Test
    void testCriarClienteComDadosValidos() {
        Cliente cliente = retornarClienteComIdENomeornecido(1L, "João Silva");
        cliente.setEndereco(new Endereco());

        when(clienteRepositoryPort.salvar(cliente)).thenReturn(cliente);
        Cliente resultado = clienteService.criarCliente(cliente);

        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getNome());
        verify(clienteRepositoryPort, times(1)).salvar(cliente);
    }

    @Test
    void testShouldReturnListOfAllClientsWhenClientsExist() {
        Cliente cliente1 = retornarClienteComIdENomeornecido(1L, "Cliente 1");
        Cliente cliente2 = retornarClienteComIdENomeornecido(2L, "Cliente 2");
        List<Cliente> clientes = List.of(cliente1, cliente2);

        when(clienteRepositoryPort.buscarTodos()).thenReturn(clientes);
        List<Cliente> result = clienteService.buscarTodosClientes();

        assertEquals(2, result.size());
        assertEquals("Cliente 1", result.get(0).getNome());
        assertEquals("Cliente 2", result.get(1).getNome());
    }

    @Test
    void testShouldReturnListOfClientsWhenValidNameIsProvided() {
        List<Cliente> expectedClientes = List.of(new Cliente());
        when(clienteRepositoryPort.buscar(NOME_CLIENTE)).thenReturn(expectedClientes);

        List<Cliente> actualClientes = clienteService.buscarCliente(NOME_CLIENTE);

        assertEquals(expectedClientes, actualClientes);
        verify(clienteRepositoryPort, times(1)).buscar(NOME_CLIENTE);
    }

    @Test
    void testUpdateSingleExistingClientSuccessfully() {
        Cliente existingCliente = retornarClienteComIdENomeornecido(1L, NOME_CLIENTE);
        Cliente updatedCliente = retornarClienteComIdENomeornecido(1L, "John Doe Updated");

        when(clienteRepositoryPort.buscar(NOME_CLIENTE)).thenReturn(List.of(existingCliente));
        when(clienteRepositoryPort.salvar(existingCliente)).thenReturn(existingCliente);

        Cliente result = clienteService.atualizarCliente(NOME_CLIENTE, updatedCliente);

        assertNotNull(result);
        verify(clienteMapper).updateCliente(updatedCliente, existingCliente);
        verify(clienteRepositoryPort).salvar(existingCliente);
    }

    @Test
    void testDeletarClienteSuccessfullyDeletesClientWhenExists() {
        List<Cliente> clientes = List.of(new Cliente());

        when(clienteRepositoryPort.buscar(NOME_CLIENTE)).thenReturn(clientes);
        clienteService.deletarCliente(NOME_CLIENTE);

        verify(clienteRepositoryPort, times(1)).deletar(NOME_CLIENTE);
    }

    private Cliente retornarClienteComIdENomeornecido(Long id, String nome) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(nome);
        return cliente;
    }
}