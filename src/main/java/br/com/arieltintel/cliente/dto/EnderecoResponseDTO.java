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
    private String numero;
    private String cep;
    private String complemento;
    private String referencia;

    public EnderecoRequestDTO to(String numero, String complemento, String referencia){

        return EnderecoRequestDTO.builder()
                .cidade(this.cidade)
                .uf(this.uf)
                .logradouro(this.logradouro)
                .numero(numero)
                .cep(this.cep)
                .complemento(complemento)
                .referencia(referencia)
                .build();
    }

}
