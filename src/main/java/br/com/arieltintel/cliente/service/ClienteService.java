package br.com.arieltintel.cliente.service;

import br.com.arieltintel.cliente.model.Cliente;
import br.com.arieltintel.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criar(Cliente cliente){
        Cliente clienteSalvo = clienteRepository.save(cliente);
            return clienteSalvo;
    }

}
