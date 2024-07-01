package br.com.cashme.adapter.inbound;

import br.com.cashme.adapter.ClienteSwagger;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

@RestController
@RequestMapping("clientes")
public class ClienteController implements ClienteSwagger {

    private final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private final ClienteServicePort clienteServicePort;
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteServicePort clienteServicePort, ClienteMapper clienteMapper) {
        this.clienteServicePort = clienteServicePort;
        this.clienteMapper = clienteMapper;
    }

    @Transactional
    public ResponseEntity<ClienteDto> criarCliente(@RequestBody @Valid ClienteDto clienteDto) {
        Cliente clienteCriado = clienteServicePort.criarCliente(clienteMapper.toModel(clienteDto));
        if (logger.isInfoEnabled())
            logger.info(format(Constants.CLIENTE_CRIADO_COM_SUCESSO, clienteCriado.getNome(), DataUtil.converterParaFormatoEspecificado(LocalDateTime.now(), Constants.FORMATTER_DD_MM_YYYY_HH_MM_SS)));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteMapper.toDto(clienteCriado));
    }

    @Transactional
    public ResponseEntity<List<ClienteDto>> buscarTodos() {
        List<Cliente> todosOsClientes = clienteServicePort.buscarTodosClientes();
        if (logger.isInfoEnabled())
            logger.info(format(Constants.TODOS_CLIENTES_RETORNADOS_COM_SUCESSO_E_TOTAL, todosOsClientes.size()));
        return ResponseEntity.status(HttpStatus.OK).body(todosOsClientes.stream().map(clienteMapper::toDto).toList());
    }

    @Transactional
    public ResponseEntity<List<ClienteDto>> buscarCliente(@PathVariable("nome") @NotBlank String nome) {
        List<Cliente> clientes = clienteServicePort.buscarCliente(nome);

        if (clientes.isEmpty()) return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());

        if (logger.isInfoEnabled())
            logger.info(Constants.CLIENTE_ENCONTRADO_NA_BASE_DE_DADOS);

        return ResponseEntity.status(HttpStatus.OK).body(clientes.stream().map(clienteMapper::toDto).toList());
    }

    @Transactional
    public ResponseEntity<ClienteDto> atualizarCliente(@PathVariable("nome") @NotBlank String nome, @RequestBody @Valid ClienteDto clienteDto) {
        Cliente clienteAtualizado = clienteServicePort.atualizarCliente(nome, clienteMapper.toModel(clienteDto));
        if (logger.isInfoEnabled())
            logger.info(format(Constants.INFORMACOES_DO_CLIENTE_ATUALIZADAS_COM_SUCESSO, clienteAtualizado.getNome(), DataUtil.converterParaFormatoEspecificado(LocalDateTime.now(), Constants.FORMATTER_DD_MM_YYYY_HH_MM_SS)));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clienteMapper.toDto(clienteAtualizado));
    }

    @Transactional
    public void deletarCliente(@PathVariable("nome") @NotBlank String nome) {
        clienteServicePort.deletarCliente(nome);
        if (logger.isInfoEnabled())
            logger.info(format(Constants.CLIENTE_EXCLUIDO_COM_SUCESSO, nome, DataUtil.converterParaFormatoEspecificado(LocalDateTime.now(), Constants.FORMATTER_DD_MM_YYYY_HH_MM_SS)));
    }
}
