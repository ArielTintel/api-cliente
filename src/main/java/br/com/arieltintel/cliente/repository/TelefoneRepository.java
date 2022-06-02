package br.com.arieltintel.cliente.repository;

import br.com.arieltintel.cliente.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

    @Query("""
            FROM Telefone t
            JOIN Cliente c ON c.id = t.cliente.id
            WHERE c.email = :email
            """)
    List<Telefone> findByEmailCliente(String email);

    @Query("""
            FROM Telefone t
            JOIN Cliente c ON c.id = t.cliente.id
            WHERE c.cpf = :cpf
            """)
    List<Telefone> findByCpfCliente(String cpf);

}
