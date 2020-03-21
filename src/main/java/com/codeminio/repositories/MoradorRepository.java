package com.codeminio.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codeminio.model.Morador;

@Repository
public interface MoradorRepository extends CrudRepository<Morador, Long> {

	@Query(value = "SELECT u from Morador u WHERE u.cpf = ?1")
	 Morador findByCpf(String cpf);
	
	@Query(value = "select u from Morador u WHERE u.login = ?1")
	 Optional<Morador> findByLogin(String login);
	
	@Query(value = "select u from Morador u WHERE  u.email = ?1")
	 Optional<Morador> findByEmail(String email);
	
	@Query(value = "select u from Morador u WHERE  u.cpf = ?1")
	 Optional<Morador> findByCPF(String cpf);

//	@Query(value = "select * from Morador")
//	public List<Morador> buscarTodosMoradores();
	
	@Query(value = "select u from Morador u WHERE u.codigoRecuperarSenha = ?1")
	 Morador findByCodigoRecuperarSenha(String codigoRecuperarSenha);

}
