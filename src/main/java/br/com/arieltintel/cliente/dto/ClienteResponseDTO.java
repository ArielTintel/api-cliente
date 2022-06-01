package br.com.arieltintel.cliente.dto;

import br.com.arieltintel.cliente.model.Telefone;
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
    private String ddd;
    private EnderecoRequestDTO endereco;
    private List<TelefoneRequestDTO> telefones;

}
