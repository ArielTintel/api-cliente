package br.com.arieltintel.cliente.service;

import br.com.arieltintel.cliente.dto.TelefoneRequestDTO;

public interface TelefoneService {

    void updateTelefoneByCpfCliente(String cpf, TelefoneRequestDTO telefoneRequestDTO) throws Exception;

    void updateTelefoneByEmailCliente(String email, TelefoneRequestDTO telefoneRequestDTO) throws Exception;

}
