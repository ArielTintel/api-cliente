package br.com.arieltintel.cliente.service;

import br.com.arieltintel.cliente.dto.EnderecoRequestDTO;

public interface EnderecoService {

    EnderecoRequestDTO findByCpfCliente(String cpf);

    EnderecoRequestDTO findByEmailCliente(String email);

    void updateEnderecoByCpfCliente(String cpf, EnderecoRequestDTO enderecoRequestDTO);

    void updateEnderecoByEmailCliente(String email, EnderecoRequestDTO enderecoRequestDTO);

}
