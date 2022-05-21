package br.com.arieltintel.cliente.service;

import br.com.arieltintel.cliente.dto.ClienteRequestDTO;
import br.com.arieltintel.cliente.dto.ClienteResponseDTO;
import br.com.arieltintel.cliente.dto.EnderecoRequestDTO;
import br.com.arieltintel.cliente.model.Cliente;
import br.com.arieltintel.cliente.model.Endereco;
import br.com.arieltintel.cliente.repository.ClienteRepository;
import br.com.arieltintel.cliente.utils.TextoUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public interface ClienteService {

    ClienteResponseDTO criar(ClienteRequestDTO clienteRequestDTO);

    List<ClienteResponseDTO> listarClientes(String nome);

    ClienteResponseDTO consultarPorEmail(String email);

    ClienteResponseDTO consultarPorCpf(String cpf);

    void atualizarCliente(ClienteRequestDTO clienteRequestDTO, String email) throws Exception;

}