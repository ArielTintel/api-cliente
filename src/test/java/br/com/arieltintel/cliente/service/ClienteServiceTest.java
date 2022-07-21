//package br.com.arieltintel.cliente.service;
//
//import br.com.arieltintel.cliente.dto.ClienteRequestDTO;
//import br.com.arieltintel.cliente.dto.ClienteResponseDTO;
//import br.com.arieltintel.cliente.dto.EnderecoRequestDTO;
//import br.com.arieltintel.cliente.model.Cliente;
//import br.com.arieltintel.cliente.model.Endereco;
//import br.com.arieltintel.cliente.repository.ClienteRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class ClienteServiceTest {
//
//	@InjectMocks
//	private ClienteService clienteService;
//
//	@Mock
//	private ModelMapper modelMapper;
//
//	@Mock
//	private ClienteRepository clienteRepository;
//
//	private static final String EMAIL = "email@gmail.com";
//	private static final String CPF = "89841010011";
//	private static final String CPFMASCARA = "898.410.100-11";
//	private Cliente cliente;
//	private Cliente clienteSalvo;
//	private ClienteResponseDTO clienteResponseDTO;
//	private ClienteRequestDTO clienteRequestDTO;
//
//	@BeforeEach
//	private void init(){
//
//		cliente = Cliente.builder()
//				.id(1L)
//				.email(EMAIL)
//				.cpf("89841010011")
//				.nome("João")
//				.sobrenome("Souza")
//				.telefones("40028922")
//				.ddd("11")
//				.endereco(Endereco.builder()
//						.cidade("Salvador")
//						.uf("BA")
//						.cep("41390090")
//						.logradouro("Rua Arminda de Souza")
//						.numero(50L)
//						.complemento("Casa")
//						.referencia("Proximo Mercadinho")
//						.build())
//				.build();
//
//		clienteSalvo = Cliente.builder()
//				.id(1L)
//				.email(EMAIL)
//				.cpf("89841010011")
//				.nome("João")
//				.sobrenome("Souza")
//				.telefone("40028922")
//				.ddd("11")
//				.endereco(Endereco.builder()
//						.cidade("Salvador")
//						.uf("BA")
//						.cep("41390090")
//						.logradouro("Rua Arminda de Souza")
//						.numero(50L)
//						.complemento("Casa")
//						.referencia("Proximo Mercadinho")
//						.build())
//				.build();
//
//		clienteResponseDTO = ClienteResponseDTO.builder()
//				.email(EMAIL)
//				.cpf("89841010011")
//				.nomeCompleto("João Souza")
//				.telefone("40028922")
//				.ddd("11")
//				.endereco(EnderecoRequestDTO.builder()
//						.cidade("Salvador")
//						.uf("BA")
//						.cep("41390090")
//						.logradouro("Rua Arminda de Souza")
//						.numero(50L)
//						.complemento("Casa")
//						.referencia("Proximo Mercadinho")
//						.build())
//				.build();
//
//		clienteRequestDTO = ClienteRequestDTO.builder()
//				.email(EMAIL)
//				.cpf("89841010011")
//				.nomeCompleto("João Souza")
//				.telefone("40028922")
//				.ddd("11")
//				.endereco(EnderecoRequestDTO.builder()
//						.cidade("Salvador")
//						.uf("BA")
//						.cep("41390090")
//						.logradouro("Rua Arminda de Souza")
//						.numero(50L)
//						.complemento("Casa")
//						.referencia("Proximo Mercadinho")
//						.build())
//				.build();
//	}
//
//	@Test
//	void consultarPorEmail(){
//
//		when(clienteRepository.findByEmail(EMAIL)).thenReturn(cliente);
//		when(modelMapper.map(cliente, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);
//
//		ClienteResponseDTO clienteResponse = clienteService.consultarPorEmail(EMAIL);
//
//		Assertions.assertNotNull(clienteResponse);
//		Assertions.assertEquals(EMAIL, clienteResponse.getEmail());
//	}
//
//	@Test
//	void consultarPorCpf(){
//
//		when(clienteRepository.findByCpf(CPF)).thenReturn(cliente);
//		when(modelMapper.map(cliente, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);
//
//		ClienteResponseDTO clienteResponse = clienteService.consultarPorCpf(CPF);
//
//		Assertions.assertNotNull(clienteResponse);
//		Assertions.assertEquals(CPFMASCARA, clienteResponse.getCpf());
//	}
//
//	@Test
//	void deve_criar_um_cliente(){
//
//		when(clienteRepository.save(cliente)).thenReturn(cliente);
//
//		ClienteResponseDTO clienteResponse = clienteService.criar(clienteRequestDTO);
//
//		Assertions.assertNotNull(clienteResponseDTO);
//		Assertions.assertEquals(CPFMASCARA, clienteResponse.getCpf());
//		verify(clienteRepository,times(1)).save(cliente);
//
//	}
//}
