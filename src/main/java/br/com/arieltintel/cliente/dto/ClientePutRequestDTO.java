package br.com.arieltintel.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientePutRequestDTO {

    @Length(min = 3, max = 255)
    private String nomeCompleto;

    @CPF(message = "{cpf}")
    @Length(min = 11, max = 14)
    private String cpf;

    @Email(message = "{email}")
    private String email;

    private EnderecoRequestDTO endereco;

    private List<TelefoneRequestDTO> telefones;

}
