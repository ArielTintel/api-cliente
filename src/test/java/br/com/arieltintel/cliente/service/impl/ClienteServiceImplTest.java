package br.com.arieltintel.cliente.service.impl;

import br.com.arieltintel.cliente.client.EnderecoClient;
import br.com.arieltintel.cliente.dto.ClienteRequestDTO;
import br.com.arieltintel.cliente.dto.ClienteResponseDTO;
import br.com.arieltintel.cliente.dto.EnderecoRequestDTO;
import br.com.arieltintel.cliente.dto.EnderecoResponseDTO;
import br.com.arieltintel.cliente.dto.TelefoneResponseDTO;
import br.com.arieltintel.cliente.enums.TelefoneTipoEnum;
import br.com.arieltintel.cliente.exceptions.ClienteBadRequestException;
import br.com.arieltintel.cliente.model.Cliente;
import br.com.arieltintel.cliente.model.Endereco;
import br.com.arieltintel.cliente.model.Telefone;
import br.com.arieltintel.cliente.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private EnderecoClient enderecoClient;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ClienteRepository clienteRepository;

    private static final String EMAIL = "email@gmail.com";
    private static final String CPF = "89841010011";
    private static final String CPFMASCARA = "898.410.100-11";
    private Cliente cliente;
    private Endereco endereco;
    private Cliente clienteSalvo;
    private ClienteResponseDTO clienteResponseDTO;
    private ClienteRequestDTO clienteRequestDTO;

    private List<Telefone> telefoneList;
    private List<TelefoneResponseDTO> telefoneResponseDTOList;

    private EnderecoResponseDTO enderecoResponseDTO;

    @BeforeEach
    void init() {

        telefoneResponseDTOList = Arrays.asList(
                TelefoneResponseDTO.builder()
                        .ddd("071")
                        .numero("78956314")
                        .recado("98415530")
                        .tipo(TelefoneTipoEnum.COMERCIAL.name())
                        .build(),
                TelefoneResponseDTO.builder()
                        .ddd("071")
                        .numero("77956314")
                        .recado("98895530")
                        .tipo(TelefoneTipoEnum.COMERCIAL.name())
                        .build());

        telefoneList = List.of(
                Telefone.builder()
                        .ddd("071")
                        .numero("78956314")
                        .recado("98415530")
                        .tipo(TelefoneTipoEnum.COMERCIAL)
                        .build(),
                Telefone.builder()
                        .ddd("071")
                        .numero("77956314")
                        .recado("98895530")
                        .tipo(TelefoneTipoEnum.COMERCIAL)
                        .build());

        endereco = Endereco.builder()
                .cidade("Salvador")
                .uf("BA")
                .cep("41390090")
                .logradouro("Rua Arminda de Souza")
                .numero("50")
                .complemento("Casa")
                .referencia("Proximo Mercadinho")
                .build();

        enderecoResponseDTO = EnderecoResponseDTO.builder()
                .cidade("Salvador")
                .uf("BA")
                .cep("41390090")
                .logradouro("Rua Arminda de Souza")
                .numero("50")
                .complemento("Casa")
                .referencia("Proximo Mercadinho")
                .build();

        cliente = Cliente.builder()
                .email(EMAIL)
                .cpf("89841010011")
                .nome("João")
                .sobrenome("Souza")
                .endereco(endereco)
                .telefones(telefoneList)
                .build();

        clienteSalvo = Cliente.builder()
                .id(1L)
                .email(EMAIL)
                .cpf("89841010011")
                .nome("João")
                .sobrenome("Souza")
                .endereco(Endereco.builder()
                        .cidade("Salvador")
                        .uf("BA")
                        .cep("41390090")
                        .logradouro("Rua Arminda de Souza")
                        .numero("50")
                        .complemento("Casa")
                        .referencia("Proximo Mercadinho")
                        .build())
                .build();

        clienteResponseDTO = ClienteResponseDTO.builder()
                .email(EMAIL)
                .cpf("898.410.100-11")
                .nomeCompleto("João Souza")
                .telefones(telefoneResponseDTOList)
                .endereco(EnderecoResponseDTO.builder()
                        .cidade("Salvador")
                        .uf("BA")
                        .cep("41390090")
                        .logradouro("Rua Arminda de Souza")
                        .numero("50")
                        .complemento("Casa")
                        .referencia("Proximo Mercadinho")
                        .build())
                .build();

        clienteRequestDTO = ClienteRequestDTO.builder()
                .email(EMAIL)
                .cpf("89841010011")
                .nomeCompleto("João Souza")
                .endereco(EnderecoRequestDTO.builder()
                        .cidade("Salvador")
                        .uf("BA")
                        .cep("41390090")
                        .logradouro("Rua Arminda de Souza")
                        .complemento("Casa")
                        .referencia("Proximo Mercadinho")
                        .build())
                .build();
    }

    @Test
    void consultarPorEmail() {

        when(clienteRepository.findByEmail(EMAIL)).thenReturn(Optional.ofNullable(cliente));

        ClienteResponseDTO clienteResponse = clienteService.consultarPorEmail(EMAIL);

        Assertions.assertNotNull(clienteResponse);
        assertEquals(EMAIL, clienteResponse.getEmail());
    }

    @Test
    void consultarPorCpf() {

        when(clienteRepository.findByCpf(CPF)).thenReturn(Optional.ofNullable(cliente));

        ClienteResponseDTO clienteResponse = clienteService.consultarPorCpf(CPF);

        Assertions.assertNotNull(clienteResponse);
        assertEquals(CPFMASCARA, clienteResponse.getCpf());
    }

    @Test
    void deve_criar_um_cliente() {

        //Arrange
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(enderecoClient.getEndereco(cliente.getEndereco().getCep())).thenReturn(enderecoResponseDTO);
        when(modelMapper.map(clienteRequestDTO, Cliente.class)).thenReturn(cliente);

        // Act
        ClienteResponseDTO clienteResponse = clienteService.criar(clienteRequestDTO);

        // Assert
        Assertions.assertNotNull(clienteResponseDTO);
        assertEquals(CPFMASCARA, clienteResponse.getCpf());
        verify(clienteRepository, times(1)).save(cliente);

    }

    @Test
    void exception_ClienteBadRequestException() {
        ClienteBadRequestException exception = assertThrows(ClienteBadRequestException.class, () -> {
            clienteService.consultarPorCpf("  ");
        });
        assertEquals("CPF Inválido.", exception.getMessage());
    }

    @Test
    void deletarCliente() throws Exception {
        cliente.setId(1L);
        when(clienteRepository.findByEmail(EMAIL)).thenReturn(Optional.ofNullable(cliente));
        clienteService.deletarCliente(EMAIL);
        verify(clienteRepository, times(1)).deleteById(cliente.getId());
    }

    @Test
    void atualizarCliente() {
    }

}
