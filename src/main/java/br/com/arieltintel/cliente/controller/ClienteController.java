package br.com.arieltintel.cliente.controller;

import br.com.arieltintel.cliente.model.Cliente;
import br.com.arieltintel.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public Cliente criar(@RequestBody Cliente cliente){
        Cliente clienteSalvo = clienteService.criar(cliente);
        return clienteSalvo;
    }

}
