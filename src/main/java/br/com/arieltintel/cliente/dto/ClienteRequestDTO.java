package br.com.arieltintel.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {

    @NotBlank(message = "{nomeCompleto.notBlank}")
    @Length(min = 3, max = 255)
    private String nomeCompleto;

    @NotBlank(message = "{cpf.notBlank}")
    @CPF(message = "{cpf}")
    @Length(min = 11, max = 14)
    private String cpf;

    @NotBlank(message = "{email.notBlank}")
    @Email(message = "{email}")
    private String email;

    private EnderecoRequestDTO endereco;

    private List<TelefoneRequestDTO> telefones;

}
