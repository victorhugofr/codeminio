package com.codeminio.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeminio.exceptions.ErroAutenticacao;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.model.Funcionario;
import com.codeminio.repositories.FuncionarioRepository;
import com.codeminio.services.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	public FuncionarioServiceImpl(FuncionarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Funcionario autenticar(String login, String senha) {
		Optional<Funcionario> funcionario = repository.findByLogin(login);

		if (!funcionario.isPresent() || !funcionario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Login ou senha inválidos");
		}

		return funcionario.get();
	}

	@Override
	@Transactional
	public Funcionario salvarFuncionario(Funcionario funcionario) {
		validarLogin(funcionario.getLogin());
		return repository.save(funcionario);
	}

	@Override
	public void validarLogin(String email) {
		boolean existe = repository.existsByEmail(email);
		if (existe) {
			throw new RegraNegocioException("Já existe um Funcionario cadastrado com este login.");
		}
	}

	public List<Funcionario> listarFuncionarioes() {
		return (List<Funcionario>) repository.findAll();
	}

	public Optional<Funcionario> procurarPorId(Long id) {
		return repository.findById(id);
	}

	public void deletarPorId(Long id) {
		repository.deleteById(id);
	}

}
