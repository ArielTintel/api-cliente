package br.com.arieltintel.cliente.repository;

import br.com.arieltintel.cliente.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("""
            FROM Endereco ec
            JOIN Cliente c ON ec.id = c.id
            WHERE c.email = :email
            """)
    Optional<Endereco> findByEmailCliente(String email);

    @Query("""
            FROM Endereco ec
            JOIN Cliente c ON ec.id = c.id
            WHERE c.cpf = :cpf
            """)
    Optional<Endereco> findByCpfCliente(String cpf);

}
