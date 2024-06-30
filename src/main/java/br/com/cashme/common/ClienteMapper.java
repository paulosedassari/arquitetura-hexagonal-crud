package br.com.cashme.common;

import br.com.cashme.adapter.outbound.entity.ClienteEntity;
import br.com.cashme.application.dto.ClienteDto;
import br.com.cashme.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    private final ModelMapper modelMapper;

    public ClienteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClienteDto toDto(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDto.class);
    }

    public ClienteEntity toEntity(Cliente cliente) {
        return modelMapper.map(cliente, ClienteEntity.class);
    }

    public Cliente toModel(ClienteDto clienteDto) {
        return modelMapper.map(clienteDto, Cliente.class);
    }

    public Cliente toModel(ClienteEntity clienteEntity) {
        return modelMapper.map(clienteEntity, Cliente.class);
    }

    public void updateCliente(Cliente clienteAlteracoes, Cliente clienteExistente) {
        modelMapper.map(clienteAlteracoes, clienteExistente);
    }
}
