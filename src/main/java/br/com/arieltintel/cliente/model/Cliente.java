package br.com.arieltintel.cliente.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @SequenceGenerator(name = "cliente_sequece", sequenceName = "cliente_sequece", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String ddd;
    private String telefone;

}
