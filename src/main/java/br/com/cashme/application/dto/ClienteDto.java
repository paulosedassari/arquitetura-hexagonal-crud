package br.com.cashme.application.dto;

import br.com.cashme.domain.model.Cliente;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClienteDto implements Serializable {

    private Long id;
    private String nome;
    private EnderecoDto endereco;

    public ClienteDto(Long id, String nome, EnderecoDto endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public ClienteDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.endereco = new EnderecoDto(cliente.getEndereco());
    }
}
