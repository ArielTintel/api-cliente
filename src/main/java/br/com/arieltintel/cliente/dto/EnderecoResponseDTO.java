package br.com.arieltintel.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponseDTO {

    private String cidade;
    private String uf;
    private String logradouro;
    private Long numero;
    private String cep;
    private String complemento;
    private String referencia;

}
