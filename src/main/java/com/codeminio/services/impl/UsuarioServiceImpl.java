package com.codeminio.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeminio.exceptions.ErroAutenticacao;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.model.Usuario;
import com.codeminio.repositories.UsuarioRepository;
import com.codeminio.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}
	@Override
	public Usuario autenticar(String login, String senha) {
		Optional<Usuario> usuario = repository.findByLogin(login);
		
		if(!usuario.isPresent() || !usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Usuário ou senha inválidos");
		}
		
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarLogin(usuario.getLogin());
		return repository.save(usuario);
	}

	@Override
	public void validarLogin(String email) {
		boolean existe =repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este login.");
		}
	}

}
