package br.com.cashme.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class PadraoErroDto implements Serializable {

    @JsonProperty("data_hora")
    private String dataHora;

    @JsonProperty("mensagem_curta")
    private String msgCurta;

    @JsonProperty("mensagem_detalhada")
    private String detalhes;

    private String endpoint;
}
