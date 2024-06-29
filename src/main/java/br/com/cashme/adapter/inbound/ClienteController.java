package br.com.cashme.adapter.inbound;

import br.com.cashme.application.dto.ClienteDto;
import br.com.cashme.application.port.ClienteServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteServicePort clienteServicePort;

    public ClienteController(ClienteServicePort clienteServicePort) {
        this.clienteServicePort = clienteServicePort;
    }

    @PostMapping
    public void criarCliente(@RequestBody ClienteDto clienteDto) {
        clienteServicePort.criarCliente(clienteDto);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> buscarTodos() {
        List<ClienteDto> todosOsClientes = clienteServicePort.buscarTodosClientes();
        return ResponseEntity.status(HttpStatus.OK).body(todosOsClientes);
    }

    @GetMapping("{nome}")
    public ResponseEntity<ClienteDto> buscarCliente(@PathVariable("nome") String nome) {
        ClienteDto cliente = clienteServicePort.buscarCliente(nome);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PutMapping("{nome}")
    public void atualizarCliente(String nome, ClienteDto clienteDto) {
        clienteServicePort.atualizarCliente(nome, clienteDto);
    }

    @DeleteMapping("{nome}")
    public void deletarCliente(String nome) {
        clienteServicePort.deletarCliente(nome);
    }
}
