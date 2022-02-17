package br.com.arieltintel.cliente;

import br.com.arieltintel.cliente.dto.ClienteResponseDTO;
import br.com.arieltintel.cliente.model.Cliente;
import br.com.arieltintel.cliente.repository.ClienteRepository;
import br.com.arieltintel.cliente.service.ClienteService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
class ClienteApplicationTests {

	@InjectMocks
	private ClienteService service;

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private ClienteRepository clienteRepository;



	private static final String EMAIL = "email@gmail.com";
	private static final String CPF = "89841010011";
	private Cliente cliente;
	private ClienteResponseDTO clienteResponseDTO;

	@BeforeEach
	private void init(){

		cliente = Cliente.builder()
				.email(EMAIL)
				.cpf("89841010011")
				.nome("João")
				.telefone("40028922")
				.ddd("11")
				.id(1L)
				.build();

		clienteResponseDTO = ClienteResponseDTO.builder()
				.email(EMAIL)
				.cpf("89841010011")
				.nome("João")
				.telefone("40028922")
				.ddd("11")
				.build();
	}

	@Test
	void consultarPorEmail(){

		when(clienteRepository.findByEmail(EMAIL)).thenReturn(cliente);
		when(modelMapper.map(cliente, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);

		ClienteResponseDTO clienteResponse = service.consultarPorEmail(EMAIL);

		Assertions.assertNotNull(clienteResponse);
		Assertions.assertEquals(EMAIL, clienteResponse.getEmail());
	}

	@Test
	void consultarPorCpf(){

		when(clienteRepository.findByCpf(CPF)).thenReturn(cliente);
		when(modelMapper.map(cliente, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);

		ClienteResponseDTO clienteResponse = service.consultarPorCpf(CPF);

		Assertions.assertNotNull(clienteResponse);
		Assertions.assertEquals(CPF, clienteResponse.getCpf());
	}

}
