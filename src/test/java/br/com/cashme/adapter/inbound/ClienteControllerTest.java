package br.com.cashme.adapter.inbound;

import br.com.cashme.application.dto.ClienteDto;
import br.com.cashme.application.port.ClienteServicePort;
import br.com.cashme.common.ClienteMapper;
import br.com.cashme.domain.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    private static final String NOME_CLIENTE = "John Doe";

    ClienteServicePort clienteServicePort;
    ClienteMapper clienteMapper;
    ClienteController clienteController;
    ClienteDto clienteDto;
    Cliente cliente;

    @BeforeEach
    public void setUp() {
        clienteServicePort = mock(ClienteServicePort.class);
        clienteMapper = mock(ClienteMapper.class);
        clienteController = new ClienteController(clienteServicePort, clienteMapper);

        clienteDto = getClienteDto();
        cliente = getCliente();
    }

    @Test
    void test_create_client_with_valid_data() {
        when(clienteMapper.toModel(clienteDto)).thenReturn(cliente);
        when(clienteServicePort.criarCliente(cliente)).thenReturn(cliente);
        when(clienteMapper.toDto(cliente)).thenReturn(clienteDto);

        ResponseEntity<ClienteDto> response = clienteController.criarCliente(clienteDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(clienteDto, response.getBody());
    }

    @Test
    void test_fetch_all_clients() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);

        List<ClienteDto> clienteDtos = new ArrayList<>();
        clienteDtos.add(clienteDto);

        when(clienteServicePort.buscarTodosClientes()).thenReturn(clientes);
        when(clienteMapper.toDto(cliente)).thenReturn(clienteDto);

        ResponseEntity<List<ClienteDto>> response = clienteController.buscarTodos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteDtos, response.getBody());
    }

    @Test
    void test_should_return_list_of_ClienteDto_when_clients_with_given_name_exist() {
        List<Cliente> clientes = List.of(new Cliente());
        when(clienteServicePort.buscarCliente("John")).thenReturn(clientes);
        when(clienteMapper.toDto(any(Cliente.class))).thenReturn(new ClienteDto());

        ResponseEntity<List<ClienteDto>> response = clienteController.buscarCliente("John");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(Objects.requireNonNull(response.getBody()).isEmpty());
    }

    @Test
    void testSuccessfullyUpdatesExistingClientWithValidData() {
        when(clienteMapper.toModel(clienteDto)).thenReturn(cliente);
        when(clienteServicePort.atualizarCliente(NOME_CLIENTE, cliente)).thenReturn(cliente);
        when(clienteMapper.toDto(cliente)).thenReturn(clienteDto);

        ResponseEntity<ClienteDto> response = clienteController.atualizarCliente(NOME_CLIENTE, clienteDto);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(clienteDto, response.getBody());

        verify(clienteServicePort).atualizarCliente(NOME_CLIENTE, cliente);
        verify(clienteMapper).toModel(clienteDto);
        verify(clienteMapper).toDto(cliente);
    }

    @Test
    void testDeletarClienteSucesso() {
        clienteController.deletarCliente(NOME_CLIENTE);
        verify(clienteServicePort, times(1)).deletarCliente(NOME_CLIENTE);
    }

    private static Cliente getCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome(NOME_CLIENTE);
        return cliente;
    }

    private static ClienteDto getClienteDto() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNome(NOME_CLIENTE);
        return clienteDto;
    }
}