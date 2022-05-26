package br.com.arieltintel.cliente.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "telefone")
public class Telefone {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ddd;

    private String numero;

    @ManyToOne
    @JoinColumn(name="cliente_id", nullable=false)
    private Cliente cliente;
}
