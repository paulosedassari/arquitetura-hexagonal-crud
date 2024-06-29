package br.com.cashme.domain.model;

import br.com.cashme.adapter.outbound.entity.ClienteEntity;
import br.com.cashme.application.dto.ClienteDto;

public class Cliente {

    private Long id;
    private String nome;
    private Endereco endereco;

    public Cliente(ClienteDto clienteDto) {
        this.nome = clienteDto.getNome();
        this.endereco = new Endereco(clienteDto.getEndereco());
    }

    public Cliente(ClienteEntity clienteEntity) {
        this.id = clienteEntity.getId();
        this.nome = clienteEntity.getNome();
        this.endereco = new Endereco(clienteEntity.getEndereco());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}
