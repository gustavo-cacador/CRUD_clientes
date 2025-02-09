package br.com.gustavo.CRUD.clientes.repositories;

import br.com.gustavo.CRUD.clientes.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
