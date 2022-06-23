package br.com.arieltintel.cliente.controller;

import br.com.arieltintel.cliente.dto.EnderecoRequestDTO;
import br.com.arieltintel.cliente.exceptions.EnderecoBadRequestException;
import br.com.arieltintel.cliente.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
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
        if (!StringUtils.hasText(cpf)) {
            throw new EnderecoBadRequestException("CPF Inválido ou Nulo.");
        }
        return enderecoService.findByCpfCliente(cpf);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/email/{email}")
    public EnderecoRequestDTO findByEmailCliente(@PathVariable String email){
        if (!StringUtils.hasText(email)) {
            throw new EnderecoBadRequestException("EMAIL Inválido ou Nulo.");
        }
        return enderecoService.findByEmailCliente(email);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/cpf/{cpf}")
    public void updateByCpfCliente(@PathVariable String cpf,
                                   @RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        if (!StringUtils.hasText(cpf)) {
            throw new EnderecoBadRequestException("CPF Inválido ou Nulo.");
        }
        enderecoService.updateEnderecoByCpfCliente(cpf, enderecoRequestDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/email/{email}")
    public void updateByEmailCliente(@PathVariable String email,
                                   @RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        if (!StringUtils.hasText(email)) {
            throw new EnderecoBadRequestException("EMAIL Inválido ou Nulo.");
        }
        enderecoService.updateEnderecoByEmailCliente(email, enderecoRequestDTO);
    }

}

