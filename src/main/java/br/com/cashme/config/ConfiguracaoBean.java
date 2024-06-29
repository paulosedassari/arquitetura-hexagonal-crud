package br.com.cashme.config;

import br.com.cashme.adapter.outbound.repositories.ClienteRepositoryJpa;
import br.com.cashme.application.port.ClienteRepositoryPort;
import br.com.cashme.application.port.ClienteServicePort;
import br.com.cashme.domain.repository.ClienteRepository;
import br.com.cashme.domain.service.ClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfiguracaoBean {

    @Bean
    ClienteServicePort clienteService(ClienteRepositoryPort clienteRepositoryPort) {
        return new ClienteService(clienteRepositoryPort);
    }

    @Bean
    ClienteRepositoryPort clienteRepository(ClienteRepositoryJpa clienteRepositoryJpa) {
        return new ClienteRepository(clienteRepositoryJpa);
    }
}
