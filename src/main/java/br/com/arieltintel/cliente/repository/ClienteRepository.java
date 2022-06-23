package br.com.arieltintel.cliente.repository;

import br.com.arieltintel.cliente.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long > {

    List<Cliente> findByNomeContainingIgnoreCase(String nome);
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByCpf(String cpf);

}
