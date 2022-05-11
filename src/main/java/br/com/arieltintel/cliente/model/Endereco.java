package br.com.arieltintel.cliente.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "endereco_cliente")
public class Endereco {

    @Id
    @Column(name = "cliente_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(nullable = false)
    @NotBlank(message = "Campo Cidade não pode ser inválido")
    private String cidade;

    @Column(nullable = false)
    private String uf;

    private String logradouro;

    @Column(nullable = false)
    private long numero;

    @Column(nullable = false)
    private String cep;

    private String complemento;

    private String referencia;


}