package br.com.arieltintel.cliente.service;

import br.com.arieltintel.cliente.model.Cliente;
import br.com.arieltintel.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criar(Cliente cliente){
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return clienteSalvo;
    }

    public List<Cliente> listarClientes(){
        return (List<Cliente>) clienteRepository.findAll();
    }

    public Cliente consultarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente consultarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public void atualizarCliente(Cliente cliente, Long id) throws Exception {
        Cliente clienteBase = consultarPorId(id);
        if(clienteBase == null){
            throw new Exception("Cliete não encontrado.");
        }
        cliente.setId(id);
        clienteRepository.save(cliente);
    }
}
