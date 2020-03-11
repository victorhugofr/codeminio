package com.codeminio.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeminio.exceptions.ErroAutenticacao;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.model.Morador;
import com.codeminio.repositories.MoradorRepository;
import com.codeminio.services.MoradorService;

@Service
public class MoradorServiceImpl implements MoradorService {

	@Autowired
	private MoradorRepository repository;
	
	public MoradorServiceImpl(MoradorRepository repository) {
		super();
		this.repository = repository;
	}
	@Override
	public Morador autenticar(String login, String senha) {
		Optional<Morador> morador = repository.findByLogin(login);
		
		if(!morador.isPresent() || !morador.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Usuário ou senha inválidos");
		}
		
		return morador.get();
	}

	@Override
	@Transactional
	public Morador salvarMorador(Morador morador) {
		validarLogin(morador.getLogin());
		return repository.save(morador);
	}

	@Override
	public void validarLogin(String email) {
		boolean existe =repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um morador cadastrado com este login.");
		}
	}
	
	public List<Morador> listarMoradores() {
		return (List<Morador>) repository.findAll();
	}
	
	public Optional<Morador> procurarPorId(Long id){
		return repository.findById(id);
	}

	public void deletarPorId(Long id) {
		repository.deleteById(id);
	}
}
