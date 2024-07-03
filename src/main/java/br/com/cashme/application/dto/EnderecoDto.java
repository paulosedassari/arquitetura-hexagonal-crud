package br.com.cashme.application.dto;

import br.com.cashme.domain.model.Endereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoDto implements Serializable {

    private String rua;
    private int numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
}
