package br.com.arieltintel.cliente.service;

import br.com.arieltintel.cliente.dto.EnderecoRequestDTO;
import br.com.arieltintel.cliente.dto.EnderecoResponseDTO;

public interface EnderecoService {

    EnderecoRequestDTO findByClienteCpf(String cpf);

    EnderecoRequestDTO findByClienteEmail(String email);

    EnderecoResponseDTO updateEndereco(String cpf, EnderecoRequestDTO enderecoRequestDTO) throws Exception;

}
