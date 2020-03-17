package com.codeminio.services;

import com.codeminio.model.Morador;

public interface MoradorService {
	
	Morador autenticar(String login, String senha);
	Morador salvarMorador(Morador usuario);
	void validarLogin(String email);
	void validarCPF(String email);
}
