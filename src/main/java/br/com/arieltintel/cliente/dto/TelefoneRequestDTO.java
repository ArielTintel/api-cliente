package br.com.arieltintel.cliente.dto;

import br.com.arieltintel.cliente.enums.TelefoneTipoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneRequestDTO {

    @Length(min = 2, max = 3)
    @NotBlank
    private String ddd;

    @Length(min = 5, max = 20)
    @NotBlank
    private String numero;

    @NotNull
    private TelefoneTipoEnum tipo;

    private String recado;

}
