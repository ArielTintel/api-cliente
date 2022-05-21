package br.com.arieltintel.cliente.service;

import br.com.arieltintel.cliente.dto.EnderecoRequestDTO;
import br.com.arieltintel.cliente.dto.EnderecoResponseDTO;

public interface EnderecoService {

    EnderecoRequestDTO findByCpfCliente(String cpf);

    EnderecoRequestDTO findByEmailCliente(String email);

    void updateEnderecoByCpfCliente(String cpf, EnderecoRequestDTO enderecoRequestDTO) throws Exception;

}
