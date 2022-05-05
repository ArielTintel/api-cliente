package br.com.arieltintel.cliente.model;

import lombok.*;

import javax.persistence.*;
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
    private Long Id;

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