package com.codeminio.services;

import com.codeminio.model.Funcionario;

public interface FuncionarioService {
	
	Funcionario autenticar(String login, String senha);
	Funcionario salvarFuncionario(Funcionario usuario);
	void validarLogin(String email);
}
