package br.com.cashme.config;

import br.com.cashme.adapter.outbound.repositories.ClienteRepositoryJpa;
import br.com.cashme.application.port.ClienteRepositoryPort;
import br.com.cashme.application.port.ClienteServicePort;
import br.com.cashme.common.ClienteMapper;
import br.com.cashme.domain.repository.ClienteRepository;
import br.com.cashme.domain.service.ClienteService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfiguracaoBean {

    @Bean
    ClienteServicePort clienteService(ClienteRepositoryPort clienteRepositoryPort, ClienteMapper clienteMapper) {
        return new ClienteService(clienteRepositoryPort, clienteMapper);
    }

    @Bean
    ClienteRepositoryPort clienteRepository(ClienteRepositoryJpa clienteRepositoryJpa, ClienteMapper clienteMapper) {
        return new ClienteRepository(clienteRepositoryJpa, clienteMapper);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setPropertyCondition(Conditions.isNotNull());
        return modelMapper;
    }
}
