package br.com.cashme.common.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PadraoErro implements Serializable {

    @JsonProperty("data_hora")
    private String dataHora;

    @JsonProperty("mensagem_curta")
    private String msgCurta;

    @JsonProperty("mensagem_detalhada")
    private String detalhes;

    private String endpoint;
}
