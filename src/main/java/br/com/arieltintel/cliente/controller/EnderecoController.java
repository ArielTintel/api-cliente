package br.com.arieltintel.cliente.controller;

import br.com.arieltintel.cliente.dto.EnderecoDTO;
import br.com.arieltintel.cliente.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public EnderecoDTO findByClienteCpf(@PathVariable String cpf){
        return enderecoService.findByClienteCpf(cpf);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/email/{email}")
    public EnderecoDTO findByClienteEmail(@PathVariable String email){
        return enderecoService.findByClienteEmail(email);
    }
}

