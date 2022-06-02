package br.com.arieltintel.cliente.controller;

import br.com.arieltintel.cliente.dto.TelefoneRequestDTO;
import br.com.arieltintel.cliente.service.TelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("cliente/telefone")
public class TelefoneController {

    @Autowired
    private TelefoneService telefoneService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/cpf/{cpf}")
    public void updateByCpfCliente(@PathVariable String cpf,
                                   @RequestBody TelefoneRequestDTO telefoneRequestDTO) throws Exception {
        if (!StringUtils.hasText(cpf)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF Invalid or Null.");
        }
        telefoneService.updateTelefoneByCpfCliente(cpf, telefoneRequestDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/email/{email}")
    public void updateByEmailCliente(@PathVariable String email,
                                   @RequestBody TelefoneRequestDTO telefoneRequestDTO) throws Exception {
        if (!StringUtils.hasText(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF Invalid or Null.");
        }
        telefoneService.updateTelefoneByEmailCliente(email, telefoneRequestDTO);
    }

}

