package br.com.arieltintel.cliente.service;

import br.com.arieltintel.cliente.dto.ClientePutRequestDTO;
import br.com.arieltintel.cliente.dto.ClienteRequestDTO;
import br.com.arieltintel.cliente.dto.ClienteResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {

    ClienteResponseDTO criar(ClienteRequestDTO clienteRequestDTO);

    void atualizarCliente(ClientePutRequestDTO clientePutRequestDTO, String email) throws Exception;

    List<ClienteResponseDTO> listarClientes(String nome);

    ClienteResponseDTO consultarPorEmail(String email);

    ClienteResponseDTO consultarPorCpf(String cpf);

    void deletarCliente(String email) throws Exception;
}