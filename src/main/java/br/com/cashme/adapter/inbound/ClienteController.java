package br.com.cashme.adapter.inbound;

import br.com.cashme.application.dto.ClienteDto;
import br.com.cashme.application.port.ClienteServicePort;
import br.com.cashme.common.ClienteMapper;
import br.com.cashme.common.Constants;
import br.com.cashme.common.DataUtil;
import br.com.cashme.domain.model.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private final ClienteServicePort clienteServicePort;
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteServicePort clienteServicePort, ClienteMapper clienteMapper) {
        this.clienteServicePort = clienteServicePort;
        this.clienteMapper = clienteMapper;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ClienteDto> criarCliente(@RequestBody @Valid ClienteDto clienteDto) {
        Cliente clienteCriado = clienteServicePort.criarCliente(clienteMapper.toModel(clienteDto));
        if (logger.isInfoEnabled())
            logger.info(format(Constants.CLIENTE_CRIADO_COM_SUCESSO, clienteCriado.getNome(), DataUtil.converterParaFormatoEspecificado(LocalDateTime.now(), Constants.FORMATTER_DD_MM_YYYY_HH_MM_SS)));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteMapper.toDto(clienteCriado));
    }

    @Transactional
    @GetMapping
    public ResponseEntity<List<ClienteDto>> buscarTodos() {
        List<Cliente> todosOsClientes = clienteServicePort.buscarTodosClientes();
        if (logger.isInfoEnabled())
            logger.info(format(Constants.TODOS_CLIENTES_RETORNADOS_COM_SUCESSO_E_TOTAL, todosOsClientes.size()));
        return ResponseEntity.status(HttpStatus.OK).body(todosOsClientes.stream().map(clienteMapper::toDto).toList());
    }

    @Transactional
    @GetMapping("{nome}")
    public ResponseEntity<ClienteDto> buscarCliente(@PathVariable("nome") @NotBlank String nome) {
        Cliente cliente = clienteServicePort.buscarCliente(nome);
        if (Objects.isNull(cliente.getId())) return ResponseEntity.status(HttpStatus.OK).build();
        if (logger.isInfoEnabled())
            logger.info(format(Constants.CLIENTE_ENCONTRADO_NA_BASE_DE_DADOS, cliente.getNome()));
        return ResponseEntity.status(HttpStatus.OK).body(clienteMapper.toDto(cliente));
    }

    @Transactional
    @PutMapping("{nome}")
    public ResponseEntity<ClienteDto> atualizarCliente(@PathVariable("nome") @NotBlank String nome, @RequestBody @Valid ClienteDto clienteDto) {
        Cliente clienteAtualizado = clienteServicePort.atualizarCliente(nome, clienteMapper.toModel(clienteDto));
        if (logger.isInfoEnabled())
            logger.info(format(Constants.INFORMACOES_DO_CLIENTE_ATUALIZADAS_COM_SUCESSO, clienteAtualizado.getNome(), DataUtil.converterParaFormatoEspecificado(LocalDateTime.now(), Constants.FORMATTER_DD_MM_YYYY_HH_MM_SS)));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clienteMapper.toDto(clienteAtualizado));
    }

    @Transactional
    @DeleteMapping("{nome}")
    public void deletarCliente(@PathVariable("nome") @NotBlank String nome) {
        clienteServicePort.deletarCliente(nome);
        if (logger.isInfoEnabled())
            logger.info(format(Constants.CLIENTE_EXCLUIDO_COM_SUCESSO, nome, DataUtil.converterParaFormatoEspecificado(LocalDateTime.now(), Constants.FORMATTER_DD_MM_YYYY_HH_MM_SS)));
    }
}
