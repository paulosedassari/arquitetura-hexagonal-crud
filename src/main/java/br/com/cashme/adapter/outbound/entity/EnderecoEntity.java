package br.com.cashme.adapter.outbound.entity;

import br.com.cashme.domain.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String rua;

    @Column
    private int numero;

    @NotBlank
    @Column
    private String bairro;

    @NotBlank
    @Column
    private String cep;

    @NotBlank
    @Column
    private String cidade;

    @NotBlank
    @Column
    private String estado;

    public EnderecoEntity(Endereco endereco) {
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
        this.cep = endereco.getCep();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
    }
}
