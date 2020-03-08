package com.codeminio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeminio.model.Morador;

@Repository
public interface MoradorRepository extends JpaRepository<Morador, Long> {

}
