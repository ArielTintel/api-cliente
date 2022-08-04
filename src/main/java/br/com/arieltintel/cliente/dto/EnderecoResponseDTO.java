package br.com.arieltintel.cliente.dto;

import br.com.arieltintel.cliente.model.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoResponseDTO implements Serializable {

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
