package br.com.arieltintel.cliente.dto;

import br.com.arieltintel.cliente.model.Cliente;
import br.com.arieltintel.cliente.utils.TextoUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteResponseDTO implements Serializable {

    private String nomeCompleto;
    private String cpf;
    private String email;
    private EnderecoResponseDTO endereco;

    private List<TelefoneResponseDTO> telefones;

    public ClienteResponseDTO to(Cliente cliente){
        return ClienteResponseDTO.builder()
                .cpf(TextoUtils.adicionarMascaraCPF(cliente.getCpf()))
                .email(cliente.getEmail())
                .nomeCompleto(cliente.getNome() + " "+ cliente.getSobrenome())
                .endereco(cliente.getEndereco() == null ? null :
                        EnderecoResponseDTO.builder()
                                .uf(cliente.getEndereco().getUf())
                                .cep(cliente.getEndereco().getCep())
                                .cidade(cliente.getEndereco().getCidade())
                                .complemento(cliente.getEndereco().getComplemento())
                                .logradouro(cliente.getEndereco().getLogradouro())
                                .numero(cliente.getEndereco().getNumero())
                                .referencia(cliente.getEndereco().getReferencia())
                                .build()
                )
                .telefones(
                        cliente.getTelefones().stream().map(telefone -> TelefoneResponseDTO.builder()
                                .tipo(telefone.getTipo().name())
                                .ddd(telefone.getDdd())
                                .recado(telefone.getRecado())
                                .numero(telefone.getNumero())
                                .build()).collect(Collectors.toList())
                )
                .build();
    }
}
