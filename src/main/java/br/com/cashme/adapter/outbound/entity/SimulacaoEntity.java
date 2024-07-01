package br.com.cashme.adapter.outbound.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "simulacao")
public class SimulacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @NotBlank
    @Column(name = "valor_solicitado")
    private BigDecimal valorSolicitado;

    @NotBlank
    @Column(name = "valor_garantia")
    private BigDecimal valorGarantia;

    @NotBlank
    @Column(name = "qtd_meses_financiamento")
    private int qtdMesesFinanciamento;

    @NotBlank
    @Column(name = "taxa_juros_mensal")
    private BigDecimal taxaJurosMensal;
}
