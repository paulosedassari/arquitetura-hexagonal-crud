package br.com.cashme.adapter.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EnderecoDto implements Serializable {

    private String rua;
    private int numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
}
