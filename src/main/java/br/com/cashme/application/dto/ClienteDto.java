package br.com.cashme.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDto implements Serializable {

    private Long id;
    private String nome;
    private EnderecoDto endereco;
}
