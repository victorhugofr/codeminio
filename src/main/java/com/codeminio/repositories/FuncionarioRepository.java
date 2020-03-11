package com.codeminio.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codeminio.model.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {

	@Query(value = "SELECT u FROM Funcionario u WHERE u.cpf = ?1")
	public Funcionario findByCpf(String cpf);
	
	@Query(value = "select u from Funcionario u where u.login = ?1")
	public Optional<Funcionario> findByLogin(String login);
	
	@Query(value = "select u from Funcionario u where u.email = ?1")
	public Optional<Funcionario> findByEmail(String email);

	boolean existsByEmail(String email);
	
	@Query(value = "select u from Funcionario u where u.codigoRecuperarSenha = ?1")
	public Funcionario findByCodigoRecuperarSenha(String codigoRecuperarSenha);

}
