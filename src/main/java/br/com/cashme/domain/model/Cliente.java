package br.com.cashme.domain.model;

public class Cliente {

    private Long id;
    private String nome;
    private Endereco endereco;

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
