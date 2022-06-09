package br.com.arieltintel.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneResponseDTO implements Serializable {

    private String ddd;
    private String numero;

    private String tipo;

    private String recado;

}
