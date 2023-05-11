package com.teste.demo.repositorioCliente;

import com.teste.demo.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositorioCliente extends CrudRepository<Cliente, Long> {

}
