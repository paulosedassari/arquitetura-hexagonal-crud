package br.com.cashme.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Simulacao {

    private Long id;
    private Cliente cliente;
    private LocalDateTime dataHora;
    private BigDecimal valorSolicitado;
    private BigDecimal valorGarantia;
    private int qtdMesesFinanciamento;
    private BigDecimal taxaJurosMensal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public BigDecimal getValorSolicitado() {
        return valorSolicitado;
    }

    public void setValorSolicitado(BigDecimal valorSolicitado) {
        this.valorSolicitado = valorSolicitado;
    }

    public BigDecimal getValorGarantia() {
        return valorGarantia;
    }

    public void setValorGarantia(BigDecimal valorGarantia) {
        this.valorGarantia = valorGarantia;
    }

    public int getQtdMesesFinanciamento() {
        return qtdMesesFinanciamento;
    }

    public void setQtdMesesFinanciamento(int qtdMesesFinanciamento) {
        this.qtdMesesFinanciamento = qtdMesesFinanciamento;
    }

    public BigDecimal getTaxaJurosMensal() {
        return taxaJurosMensal;
    }

    public void setTaxaJurosMensal(BigDecimal taxaJurosMensal) {
        this.taxaJurosMensal = taxaJurosMensal;
    }
}
