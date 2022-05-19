package br.com.arieltintel.cliente.controller;

import br.com.arieltintel.cliente.dto.EnderecoRequestDTO;
import br.com.arieltintel.cliente.dto.EnderecoResponseDTO;
import br.com.arieltintel.cliente.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cliente/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cpf/{cpf}")
    public EnderecoRequestDTO findByCpfCliente(@PathVariable String cpf){
        return enderecoService.findByCpfCliente(cpf);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/email/{email}")
    public EnderecoRequestDTO findByEmailCliente(@PathVariable String email){
        return enderecoService.findByEmailCliente(email);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{cpf}")
    public EnderecoResponseDTO updateByCpfCliente(@PathVariable String cpf,
                                                  @RequestBody EnderecoRequestDTO enderecoRequestDTO) throws Exception {
        return enderecoService.updateEnderecoByCpfCliente(cpf, enderecoRequestDTO);
    }

}

