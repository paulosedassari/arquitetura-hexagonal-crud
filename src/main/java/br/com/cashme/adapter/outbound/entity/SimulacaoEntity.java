package br.com.cashme.adapter.outbound.entity;

import br.com.cashme.domain.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(name = "valor_solicitado")
    private BigDecimal valorSolicitado;

    @Column(name = "valor_garantia")
    private BigDecimal valorGarantia;

    @Column(name = "qtd_meses_financiamento")
    private int qtdMesesFinanciamento;

    @Column(name = "taxa_juros_mensal")
    private BigDecimal taxaJurosMensal;
}
