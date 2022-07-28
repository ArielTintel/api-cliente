package br.com.arieltintel.cliente.service.impl;

import br.com.arieltintel.cliente.client.EnderecoClient;
import br.com.arieltintel.cliente.dto.ClientePutRequestDTO;
import br.com.arieltintel.cliente.dto.ClienteRequestDTO;
import br.com.arieltintel.cliente.dto.ClienteResponseDTO;
import br.com.arieltintel.cliente.dto.EnderecoResponseDTO;
import br.com.arieltintel.cliente.dto.TelefoneResponseDTO;
import br.com.arieltintel.cliente.exceptions.CepNotFoundException;
import br.com.arieltintel.cliente.exceptions.ClienteBadRequestException;
import br.com.arieltintel.cliente.exceptions.ClienteNotFoundException;
import br.com.arieltintel.cliente.model.Cliente;
import br.com.arieltintel.cliente.model.Endereco;
import br.com.arieltintel.cliente.repository.ClienteRepository;
import br.com.arieltintel.cliente.service.ClienteService;
import br.com.arieltintel.cliente.utils.TextoUtils;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Log4j2
@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    private final EnderecoClient enderecoClient;

    private final ModelMapper modelMapper;

    public static final String ESPACO = " ";
    public static final int ZERO = 0;

    public ClienteServiceImpl(ClienteRepository clienteRepository, EnderecoClient enderecoClient, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.enderecoClient = enderecoClient;
        this.modelMapper = modelMapper;
    }

    @CacheEvict(value = "clientes", allEntries = true)
    @Transactional
    public ClienteResponseDTO criar(ClienteRequestDTO clienteRequestDTO) {

        setEndereco(clienteRequestDTO);

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

    private void setEndereco(ClienteRequestDTO clienteRequestDTO) {

        if (!ObjectUtils.isEmpty(clienteRequestDTO.getEndereco())) {
                String cep = clienteRequestDTO.getEndereco().getCep();
            try {
                EnderecoResponseDTO enderecoResponseDTO = enderecoClient.getEndereco(cep);
                clienteRequestDTO.setEndereco(enderecoResponseDTO.to(clienteRequestDTO.getEndereco().getNumero(),
                        clienteRequestDTO.getEndereco().getComplemento(),
                        clienteRequestDTO.getEndereco().getReferencia()));
            } catch (FeignException e) {
                log.error("Erro ao acessar a api endereco: " + e);
                if (HttpStatus.NOT_FOUND.value() == e.status()) {
                    throw new CepNotFoundException(cep);
                }
            }
        }
    }

    @Cacheable("clientes")
    public List<ClienteResponseDTO> listarClientes(String nome) {

        List<Cliente> clienteList;

        if (StringUtils.hasText(nome)) {
            clienteList = clienteRepository.findByNomeContainingIgnoreCase(nome);
        } else {
            clienteList = (List<Cliente>) clienteRepository.findAll();
        }

        Collections.sort(clienteList, Comparator.comparing(Cliente::getNome));

        List<ClienteResponseDTO> clienteResponseDTOList = new ArrayList<>();

        clienteList.forEach(cliente -> {
            ClienteResponseDTO clienteResponseDTO = convertClienteResponseDTO(cliente);
            setTelefone(cliente, clienteResponseDTO);
            clienteResponseDTOList.add(clienteResponseDTO);
        });

        return clienteResponseDTOList;
    }

    @Cacheable("clientes")
    public ClienteResponseDTO consultarPorCpf(String cpf) {
        cpf = TextoUtils.removeEspecialCaracter(cpf);
        if (TextoUtils.contemTexto(cpf)) {
            Cliente cliente = clienteRepository.findByCpf(cpf)
                    .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado para o CPF indicado."));

            ClienteResponseDTO clienteResponseDTO = modelMapper.map(cliente, ClienteResponseDTO.class);
            setTelefone(cliente, clienteResponseDTO);
            return clienteResponseDTO;
        } else {
            throw new ClienteBadRequestException("CPF Inválido.");
        }
    }

    @CacheEvict(value = "clientes", allEntries = true)
    public void deletarCliente(String email) throws Exception {
        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(ClienteNotFoundException::new);

        clienteRepository.deleteById(cliente.getId());
    }

    @Cacheable("clientes")
    public ClienteResponseDTO consultarPorEmail(String email) {
        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(ClienteNotFoundException::new);
        ClienteResponseDTO clienteResponseDTO = modelMapper.map(cliente, ClienteResponseDTO.class);
        setTelefone(cliente, clienteResponseDTO);
        return clienteResponseDTO;
    }

    @CacheEvict(value = "clientes", allEntries = true)
    public void atualizarCliente(ClientePutRequestDTO clientePutRequestDTO, String email) {
        Cliente clienteOp = clienteRepository.findByEmail(email)
                .orElseThrow(ClienteNotFoundException::new);
        modelMapper.map(clientePutRequestDTO, clienteOp);
        clienteRepository.save(clienteOp);
    }


    private Cliente convertCliente(ClienteRequestDTO clienteRequestDTO) {
        clienteRequestDTO.setCpf(TextoUtils.removeEspecialCaracter(clienteRequestDTO.getCpf()));
        Cliente cliente = modelMapper.map(clienteRequestDTO, Cliente.class);
        setNomeSobreNome(clienteRequestDTO, cliente);
        cliente.setEndereco(null);
        cliente.getTelefones().stream().forEach(telefone -> telefone.setCliente(cliente));

        return cliente;
    }

    private void setNomeSobreNome(ClienteRequestDTO clienteRequestDTO, Cliente cliente) {
        int delimitadorIndex = clienteRequestDTO.getNomeCompleto().indexOf(ESPACO);
        String nome = clienteRequestDTO.getNomeCompleto().substring(ZERO, delimitadorIndex);
        String sobrenome = clienteRequestDTO.getNomeCompleto().substring(delimitadorIndex + 1, clienteRequestDTO.getNomeCompleto().length());

        cliente.setNome(nome);
        cliente.setSobrenome(sobrenome);
    }

    private ClienteResponseDTO convertClienteResponseDTO(Cliente clienteSalvo) {
        ClienteResponseDTO clienteResponseDTO = modelMapper.map(clienteSalvo, ClienteResponseDTO.class);

        setTelefone(clienteSalvo, clienteResponseDTO);

        clienteResponseDTO.setNomeCompleto(clienteSalvo.getNome() + ESPACO + clienteSalvo.getSobrenome());
        clienteResponseDTO.setEmail(clienteSalvo.getEmail());
        clienteResponseDTO.setCpf(TextoUtils.adicionarMascaraCPF(clienteResponseDTO.getCpf()));
        clienteResponseDTO.setEndereco(EnderecoResponseDTO.builder()
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

    private void setTelefone(Cliente clienteSalvo, ClienteResponseDTO clienteResponseDTO) {
        if (!CollectionUtils.isEmpty(clienteSalvo.getTelefones())) {
            List<TelefoneResponseDTO> telefoneResponseDTO = new ArrayList<>();

            clienteSalvo.getTelefones().stream().forEach(telefone -> {
                telefoneResponseDTO.add(
                        TelefoneResponseDTO.builder()
                                .tipo(telefone.getTipo().getDescricao())
                                .ddd(telefone.getDdd())
                                .recado(telefone.getRecado())
                                .numero(telefone.getNumero())
                                .build()
                );
            });

            clienteResponseDTO.setTelefones(telefoneResponseDTO);
        }
    }
}
