package com.codeminio.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codeminio.model.Morador;

@Repository
public interface MoradorRepository extends CrudRepository<Morador, Long> {

}
