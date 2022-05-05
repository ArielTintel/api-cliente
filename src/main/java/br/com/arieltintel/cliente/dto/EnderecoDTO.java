package br.com.arieltintel.cliente.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    private String cidade;

    private String uf;

    private String logradouro;

    private long numero;

    private String cep;

    private String complemento;

    private String referencia;

}