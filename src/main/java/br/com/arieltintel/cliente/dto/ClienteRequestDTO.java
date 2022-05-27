package br.com.arieltintel.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {

    @NotNull(message = "Nome é Obrigatório.")
    @Length(min = 3, max = 255)
    private String nomeCompleto;

    @NotNull(message = "{cpf.notNull}")
    @CPF(message = "{cpf}")
    @Length(min = 11, max = 14)
    private String cpf;

    @NotNull(message = "{email.notNull}")
    @Email(message = "{email}")
    private String email;

    private EnderecoRequestDTO endereco;

    private List<TelefoneRequestDTO> telefones;

}
