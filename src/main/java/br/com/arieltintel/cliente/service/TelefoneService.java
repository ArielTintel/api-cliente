package br.com.arieltintel.cliente.service;

import br.com.arieltintel.cliente.dto.TelefoneRequestDTO;

public interface TelefoneService {

    void updateTelefoneByCpfCliente(String cpf, TelefoneRequestDTO telefoneRequestDTO, String dddAntigo, String telelefoneAntigo) throws Exception;

    void updateTelefoneByEmailCliente(String email, TelefoneRequestDTO telefoneRequestDTO, String dddAntigo, String telelefoneAntigo) throws Exception;

}
