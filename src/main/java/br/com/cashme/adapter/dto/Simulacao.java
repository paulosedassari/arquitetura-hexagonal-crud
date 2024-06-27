package br.com.cashme.adapter.dto;

import br.com.cashme.domain.model.Cliente;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Simulacao implements Serializable {

    private Long id;
    private Cliente cliente;
    private LocalDateTime dataHora;
    private BigDecimal valorSolicitado;
    private BigDecimal valorGarantia;
    private int qtdMesesFinanciamento;
    private int taxaJurosMensal;
}
