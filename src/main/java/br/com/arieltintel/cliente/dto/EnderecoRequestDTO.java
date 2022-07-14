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
public class EnderecoRequestDTO {

    private String cidade;

    private String uf;

    private String logradouro;

    @NotBlank(message = "Campo Número Obrigatório")
    private String numero;

    private String cep;

    private String complemento;

    private String referencia;

    private String bairro;

}