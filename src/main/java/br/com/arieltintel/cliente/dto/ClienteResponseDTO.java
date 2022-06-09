package br.com.arieltintel.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {

    private String nomeCompleto;
    private String cpf;
    private String enderecoEletronico;
    private EnderecoResponseDTO endereco;

    private List<TelefoneResponseDTO> telefones;

}
