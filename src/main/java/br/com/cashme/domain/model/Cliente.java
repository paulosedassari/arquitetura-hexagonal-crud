package br.com.cashme.domain.model;

import br.com.cashme.adapter.outbound.entity.ClienteEntity;
import br.com.cashme.application.dto.ClienteDto;

public class Cliente {

    private Long id;
    private String nome;
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(ClienteDto clienteDto) {
        this.id = clienteDto.getId();
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
