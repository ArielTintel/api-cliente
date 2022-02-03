package br.com.arieltintel.cliente.repository;

import br.com.arieltintel.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ClienteRepository extends JpaRepository<Cliente, Long > {

    public List<Cliente> findByNomeContainingIgnoreCase(String nome);
    public Cliente findByEmail(String email);
    public Cliente findByCpf(String cpf);

}
