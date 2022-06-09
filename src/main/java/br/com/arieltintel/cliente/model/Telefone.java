package br.com.arieltintel.cliente.model;

import br.com.arieltintel.cliente.enums.TelefoneTipoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    @Column(nullable = false)
    private String ddd;

    @Column(nullable = false)
    private String numero;

    private String recado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TelefoneTipoEnum tipo;

    @ManyToOne
    @JoinColumn(name="cliente_id", nullable=false)
    private Cliente cliente;

}
