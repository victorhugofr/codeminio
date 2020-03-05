package com.codeminio.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codeminio.model.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {

}