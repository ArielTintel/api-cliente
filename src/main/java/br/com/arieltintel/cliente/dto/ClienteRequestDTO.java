package br.com.arieltintel.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {


    @Length(min = 3, max = 255)
    private String nome;

    @CPF
    private String cpf;

    @Email
    private String email;

    @Length(min = 2, max = 3)
    private String ddd;

    @Length(min = 5, max = 30)
    private String telefone;

}
