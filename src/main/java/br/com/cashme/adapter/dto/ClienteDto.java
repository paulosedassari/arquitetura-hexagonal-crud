package br.com.cashme.adapter.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClienteDto implements Serializable {

    private Long id;
    private String nome;
    private EnderecoDto endereco;

}
