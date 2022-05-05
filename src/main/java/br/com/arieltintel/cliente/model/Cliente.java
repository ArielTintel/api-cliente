package br.com.arieltintel.cliente.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente {

    @Id
    @SequenceGenerator(name = "cliente_sequece", sequenceName = "cliente_sequece", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sobrenome", nullable = false)
    private String sobrenome;

    @Column(nullable = false, unique = true ,length = 11)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 3)
    private String ddd;

    @Column(length = 50)
    private String telefone;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Endereco endereco;

}
