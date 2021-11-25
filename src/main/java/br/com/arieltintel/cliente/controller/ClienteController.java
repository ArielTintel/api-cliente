package br.com.arieltintel.cliente.controller;

import br.com.arieltintel.cliente.model.Cliente;
import br.com.arieltintel.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public Cliente consultarPorId(@PathVariable Long id){
        return clienteService.consultarPorId(id);
    }

    @GetMapping("/email/{email}")
    public Cliente consultarPorEmail(@PathVariable String email){
        return clienteService.consultarPorEmail(email);
    }

    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable Long id){
        clienteService.deletarCliente(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) throws Exception {
        clienteService.atualizarCliente(cliente, id);
    }

}
