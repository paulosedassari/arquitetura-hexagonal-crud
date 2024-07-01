package br.com.cashme.adapter.outbound.entity;

import br.com.cashme.domain.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100)
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private EnderecoEntity endereco;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<SimulacaoEntity> simulacoes;

    public ClienteEntity(Cliente cliente) {
        this.nome = cliente.getNome();
        this.endereco = new EnderecoEntity(cliente.getEndereco());
    }
}
