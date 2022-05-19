package br.com.arieltintel.cliente.repository;

import br.com.arieltintel.cliente.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("""
            FROM Endereco ec
            JOIN Cliente c ON ec.id = c.id
            WHERE c.email = :email
            """)
    Endereco findByEmailCliente(String email);

    @Query("""
            FROM Endereco ec
            JOIN Cliente c ON ec.id = c.id
            WHERE c.cpf = :cpf
            """)
    Endereco findByCpfCliente(String cpf);

}
