package br.com.arieltintel.cliente.dto;

import br.com.arieltintel.cliente.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneRequestDTO {

    @Length(min = 2, max = 3)
    private String ddd;

    @Length(min = 5, max = 20)
    private String numero;

}
