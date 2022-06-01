package br.com.arieltintel.cliente.controller;

import br.com.arieltintel.cliente.dto.ClienteRequestDTO;
import br.com.arieltintel.cliente.dto.ClienteResponseDTO;
import br.com.arieltintel.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ClienteResponseDTO criar(@Validated @RequestBody ClienteRequestDTO clienteRequestDTO){

        ClienteResponseDTO clienteSalvo = clienteService.criar(clienteRequestDTO);
        return clienteSalvo;

    }

    @GetMapping
    public List<ClienteResponseDTO> listarClientes(@RequestParam(required = false) String nome){
        return clienteService.listarClientes(nome);
    }

    @GetMapping("/email/{email}")
    public ClienteResponseDTO consultarPorEmail(@PathVariable String email){
        return clienteService.consultarPorEmail(email);
    }

    @GetMapping("/cpf/{cpf}")
    public ClienteResponseDTO consultarPorCpf(@PathVariable String cpf){
        return clienteService.consultarPorCpf(cpf);
    }

    @DeleteMapping("/email/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable String email) throws Exception {
        clienteService.deletarCliente(email);
    }

    @PutMapping("/email/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(@PathVariable String email, @Validated @RequestBody ClienteRequestDTO clienteRequestDTO) throws Exception {
        clienteService.atualizarCliente(clienteRequestDTO, email);
    }
}
