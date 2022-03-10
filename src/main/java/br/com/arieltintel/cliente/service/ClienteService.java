package br.com.arieltintel.cliente.service;

import br.com.arieltintel.cliente.dto.ClienteRequestDTO;
import br.com.arieltintel.cliente.dto.ClienteResponseDTO;
import br.com.arieltintel.cliente.model.Cliente;
import br.com.arieltintel.cliente.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @CacheEvict(value = "clientes", allEntries = true)
    public ClienteResponseDTO criar(ClienteRequestDTO clienteRequestDTO){

        Cliente cliente = convertCliente(clienteRequestDTO);
        setNomeSobreNome(clienteRequestDTO, cliente);
        Cliente clienteSalvo = clienteRepository.save(cliente);

        return convertClienteResponseDTO(clienteSalvo);
    }

    @Cacheable("clientes")
    public List<ClienteResponseDTO> listarClientes(String nome){

        List<Cliente> clienteList = null;

        if(nome == null) {
            clienteList = (List<Cliente>)clienteRepository.findAll();
        } else {
            clienteList = (List<Cliente>)clienteRepository.findByNomeContainingIgnoreCase(nome);
        }

        Collections.sort(clienteList, Comparator.comparing(Cliente::getNome));

        List<ClienteResponseDTO> clienteResponseDTOList = new ArrayList<>();

        clienteList.forEach(cliente -> {
            ClienteResponseDTO clienteResponseDTO = convertClienteResponseDTO(cliente);
            clienteResponseDTOList.add(clienteResponseDTO);
        });
        return clienteResponseDTOList;
    }

    @Cacheable("clientes")
    public ClienteResponseDTO consultarPorCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    @CacheEvict(value = "clientes", allEntries = true)
    public void deletarCliente(String email) throws Exception {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new Exception("Cliete não encontrado. Verifique o Email digitado.");
        }
        clienteRepository.deleteById(cliente.getId());
    }

    @Cacheable("clientes")
    public ClienteResponseDTO consultarPorEmail(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    @CacheEvict(value = "clientes", allEntries = true)
    public void atualizarCliente(ClienteRequestDTO clienteRequestDTO, String email) throws Exception {
        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente == null){
            throw new Exception("Cliete não encontrado.");
        }
        modelMapper.map(clienteRequestDTO, cliente);
        clienteRepository.save(cliente);
    }


    private Cliente convertCliente(ClienteRequestDTO clienteRequestDTO) {
        return modelMapper.map(clienteRequestDTO, Cliente.class);
    }

    //Passagem de valor dos Objetos por referencia
    private void setNomeSobreNome(ClienteRequestDTO clienteRequestDTO, Cliente cliente) {
        int delimitadorIndex = clienteRequestDTO.getNomeCompleto().indexOf(" ");
        String nome = clienteRequestDTO.getNomeCompleto().substring(0, delimitadorIndex);
        String sobrenome = clienteRequestDTO.getNomeCompleto().substring(delimitadorIndex+1, clienteRequestDTO.getNomeCompleto().length());

        cliente.setNome(nome);
        cliente.setSobrenome(sobrenome);
    }

    private ClienteResponseDTO convertClienteResponseDTO(Cliente clienteSalvo) {
        ClienteResponseDTO clienteResponseDTO = modelMapper.map(clienteSalvo, ClienteResponseDTO.class);

        clienteResponseDTO.setNomeCompleto(clienteSalvo.getNome() + " " + clienteSalvo.getSobrenome());
        clienteResponseDTO.setEnderecoEletronico(clienteSalvo.getEmail());

        return clienteResponseDTO;
    }
}
