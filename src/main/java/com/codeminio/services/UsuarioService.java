package com.codeminio.services;

import com.codeminio.model.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String login, String senha);
	Usuario salvarUsuario(Usuario usuario);
	void validarLogin(String email);
}
