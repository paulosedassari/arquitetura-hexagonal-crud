package br.com.cashme.domain.model;

import br.com.cashme.application.dto.ClienteDto;

public class Cliente {

    private Long id;
    private String nome;
    private Endereco endereco;

    public Cliente(ClienteDto clienteDto) {
        this.nome = clienteDto.getNome();
        this.endereco = new Endereco(clienteDto.getEndereco());
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
