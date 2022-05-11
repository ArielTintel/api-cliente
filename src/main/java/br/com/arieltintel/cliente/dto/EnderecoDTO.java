package br.com.arieltintel.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    @NotBlank(message = "Cidade não pode ser Nulo ou invalido")
    private String cidade;

    @NotBlank(message = "UF não pode ser Nulo ou invalido")
    private String uf;

    @NotBlank(message = "Logradouro não pode ser Nulo ou invalido")
    private String logradouro;

    private Long numero;

    @NotBlank(message = "CEP não pode ser Nulo ou invalido")
    private String cep;

    private String complemento;

    private String referencia;

}