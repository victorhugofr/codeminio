package com.codeminio.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codeminio.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	@Query(value = "SELECT u FROM Usuario u WHERE u.cpf = ?1")
	public Usuario findByCpf(String cpf);
	
	@Query(value = "select u from Usuario u where u.login = ?1")
	public Optional<Usuario> findByLogin(String login);
	
	@Query(value = "select u from Usuario u where u.email = ?1")
	public Optional<Usuario> findByEmail(String email);

	boolean existsByEmail(String email);
	
	@Query(value = "select u from Usuario u where u.codigoRecuperarSenha = ?1")
	public Usuario findByCodigoRecuperarSenha(String codigoRecuperarSenha);

}
