package br.com.arieltintel.cliente.service;

import br.com.arieltintel.cliente.dto.EnderecoDTO;

public interface EnderecoService {

    EnderecoDTO findByClienteCpf(String cpf);

    EnderecoDTO findByClienteEmail(String email);

}
