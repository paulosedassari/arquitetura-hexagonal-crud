package br.com.cashme.adapter.outbound.repositories;

import br.com.cashme.adapter.outbound.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepositoryJpa extends JpaRepository<ClienteEntity, Long> {

    List<ClienteEntity> findByNome(String nome);

    void deleteByNome(String nome);
}
