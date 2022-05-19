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
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public static final String ESPACO = " ";
    public static final int ZERO = 0;

    @CacheEvict(value = "clientes", allEntries = true)
    @Transactional
    public ClienteResponseDTO criar(ClienteRequestDTO clienteRequestDTO){

        Cliente cliente = convertCliente(clienteRequestDTO);

        Cliente clienteSalvo = clienteRepository.save(cliente);

        clienteSalvo.setEndereco(Endereco.builder()
                .id(clienteSalvo.getId())
                .cliente(clienteSalvo)
                .cep(clienteRequestDTO.getEndereco().getCep())
                .cidade(clienteRequestDTO.getEndereco().getCidade())
                .logradouro(clienteRequestDTO.getEndereco().getLogradouro())
                .uf(clienteRequestDTO.getEndereco().getUf())
                .referencia(clienteRequestDTO.getEndereco().getReferencia())
                .numero(clienteRequestDTO.getEndereco().getNumero())
                .complemento(clienteRequestDTO.getEndereco().getComplemento())
                .build());

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
        cpf = TextoUtils.removeEspecialCaracter(cpf);
        if (!TextoUtils.contemTexto(cpf)) {
            Cliente cliente = clienteRepository.findByCpf(cpf);
            return modelMapper.map(cliente, ClienteResponseDTO.class);
        }
        return null;
    }

    @CacheEvict(value = "clientes", allEntries = true)
    public void deletarCliente(String email) throws Exception {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new Exception("Cliente não encontrado. Verifique o Email digitado.");
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
            throw new Exception("Cliente não encontrado.");
        }
        modelMapper.map(clienteRequestDTO, cliente);
        clienteRepository.save(cliente);
    }


    private Cliente convertCliente(ClienteRequestDTO clienteRequestDTO) {
        clienteRequestDTO.setCpf(TextoUtils.removeEspecialCaracter(clienteRequestDTO.getCpf()));
        Cliente cliente = modelMapper.map(clienteRequestDTO, Cliente.class);
        setNomeSobreNome(clienteRequestDTO, cliente);
        cliente.setEndereco(null);

        return cliente;
    }

    private void setNomeSobreNome(ClienteRequestDTO clienteRequestDTO, Cliente cliente) {
        int delimitadorIndex = clienteRequestDTO.getNomeCompleto().indexOf(ESPACO);
        String nome = clienteRequestDTO.getNomeCompleto().substring(ZERO, delimitadorIndex);
        String sobrenome = clienteRequestDTO.getNomeCompleto().substring(delimitadorIndex+1, clienteRequestDTO.getNomeCompleto().length());

        cliente.setNome(nome);
        cliente.setSobrenome(sobrenome);
    }

    private ClienteResponseDTO convertClienteResponseDTO(Cliente clienteSalvo) {
        ClienteResponseDTO clienteResponseDTO = modelMapper.map(clienteSalvo, ClienteResponseDTO.class);

        clienteResponseDTO.setNomeCompleto(clienteSalvo.getNome() + ESPACO + clienteSalvo.getSobrenome());
        clienteResponseDTO.setEnderecoEletronico(clienteSalvo.getEmail());
        clienteResponseDTO.setCpf(TextoUtils.adicionarMascaraCPF(clienteResponseDTO.getCpf()));
        clienteResponseDTO.setEndereco(EnderecoRequestDTO.builder()
                .complemento(clienteSalvo.getEndereco().getComplemento())
                .numero(clienteSalvo.getEndereco().getNumero())
                .referencia(clienteSalvo.getEndereco().getReferencia())
                .uf(clienteSalvo.getEndereco().getUf())
                .cep(clienteSalvo.getEndereco().getCep())
                .logradouro(clienteSalvo.getEndereco().getLogradouro())
                .cidade(clienteSalvo.getEndereco().getCidade())
                .build());
        return clienteResponseDTO;
    }
}