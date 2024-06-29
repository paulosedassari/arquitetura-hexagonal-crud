package br.com.cashme.domain.model;

import br.com.cashme.adapter.outbound.entity.EnderecoEntity;
import br.com.cashme.application.dto.EnderecoDto;

public class Endereco {

    private String rua;
    private int numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;

    public Endereco(EnderecoDto enderecoDto) {
        this.rua = enderecoDto.getRua();
        this.numero = enderecoDto.getNumero();
        this.bairro = enderecoDto.getBairro();
        this.cep = enderecoDto.getCep();
        this.cidade = enderecoDto.getCidade();
        this.estado = enderecoDto.getEstado();
    }

    public Endereco(EnderecoEntity enderecoEntity) {
        this.rua = enderecoEntity.getRua();
        this.numero = enderecoEntity.getNumero();
        this.bairro = enderecoEntity.getBairro();
        this.cep = enderecoEntity.getCep();
        this.cidade = enderecoEntity.getCidade();
        this.estado = enderecoEntity.getEstado();
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
