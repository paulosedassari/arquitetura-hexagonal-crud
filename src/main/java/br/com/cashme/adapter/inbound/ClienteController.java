package br.com.cashme.adapter.inbound;

import br.com.cashme.application.dto.ClienteDto;
import br.com.cashme.application.port.ClienteServicePort;
import br.com.cashme.common.exception.CashMeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteServicePort clienteServicePort;

    public ClienteController(ClienteServicePort clienteServicePort) {
        this.clienteServicePort = clienteServicePort;
    }

    @PostMapping
    public void criarCliente(@RequestBody @Valid ClienteDto clienteDto) {
        try {
            clienteServicePort.criarCliente(clienteDto);
        } catch (Exception e) {
            throw new CashMeException(e.getMessage(), e);
        }
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> buscarTodos() {
        try {
            List<ClienteDto> todosOsClientes = clienteServicePort.buscarTodosClientes();
            return ResponseEntity.status(HttpStatus.OK).body(todosOsClientes);
        } catch (Exception e) {
            throw new CashMeException(e.getMessage(), e);
        }
    }

    @GetMapping("{nome}")
    public ResponseEntity<ClienteDto> buscarCliente(@PathVariable("nome") @NotBlank String nome) {
        try {
            ClienteDto cliente = clienteServicePort.buscarCliente(nome);
            return ResponseEntity.status(HttpStatus.OK).body(cliente);
        } catch (Exception e) {
            throw new CashMeException(e.getMessage(), e);
        }
    }

    @PutMapping("{nome}")
    public void atualizarCliente(@PathVariable("nome") @NotBlank String nome, @RequestBody @Valid ClienteDto clienteDto) {
        try {
            clienteServicePort.atualizarCliente(nome, clienteDto);
        } catch (Exception e) {
            throw new CashMeException(e.getMessage(), e);
        }
    }

    @DeleteMapping("{nome}")
    public void deletarCliente(@PathVariable("nome") @NotBlank String nome) {
        try {
            clienteServicePort.deletarCliente(nome);
        } catch (Exception e) {
            throw new CashMeException(e.getMessage(), e);
        }
    }
}
