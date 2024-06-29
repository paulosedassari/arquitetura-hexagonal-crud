package br.com.cashme.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PadraoErro implements Serializable {

    private LocalDateTime dataHora;
    private String detalhes;
    private String endpoint;
}
