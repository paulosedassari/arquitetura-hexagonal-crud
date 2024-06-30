package br.com.cashme.adapter.inbound;

import br.com.cashme.application.dto.ClienteDto;
import br.com.cashme.application.port.ClienteServicePort;
import br.com.cashme.common.ClienteMapper;
import br.com.cashme.domain.model.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteServicePort clienteServicePort;
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteServicePort clienteServicePort, ClienteMapper clienteMapper) {
        this.clienteServicePort = clienteServicePort;
        this.clienteMapper = clienteMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void criarCliente(@RequestBody @Valid ClienteDto clienteDto) {
        Cliente cliente = clienteMapper.toModel(clienteDto);
        clienteServicePort.criarCliente(cliente);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<List<ClienteDto>> buscarTodos() {
        List<ClienteDto> todosOsClientes = clienteServicePort.buscarTodosClientes();
        return ResponseEntity.status(HttpStatus.OK).body(todosOsClientes);
    }

    @GetMapping("{nome}")
    @Transactional
    public ResponseEntity<ClienteDto> buscarCliente(@PathVariable("nome") @NotBlank String nome) {
        ClienteDto cliente = clienteServicePort.buscarCliente(nome);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PutMapping("{nome}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void atualizarCliente(@PathVariable("nome") @NotBlank String nome, @RequestBody @Valid ClienteDto clienteDto) {
        Cliente cliente = clienteMapper.toModel(clienteDto);
        clienteServicePort.atualizarCliente(nome, cliente);
    }

    @DeleteMapping("{nome}")
    @Transactional
    public void deletarCliente(@PathVariable("nome") @NotBlank String nome) {
        clienteServicePort.deletarCliente(nome);
    }
}
