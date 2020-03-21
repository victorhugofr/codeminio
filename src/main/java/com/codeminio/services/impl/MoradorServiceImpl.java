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
		validarNome(morador.getNome());
		validarApartamento(morador.getApartamento());
		validarLogin(morador.getLogin());
		validarCPF(morador.getCPF());
		return repository.save(morador);
	}

	private void validarApartamento(String apartamento) {
		if(apartamento==null)
			throw new RegraNegocioException("Apartamento é obrigatorio");
	}
	private void validarNome(String nome) {
		if(nome==null) {
			throw new RegraNegocioException("Nome é obrigatorio");
		}
		
	}
	@Override
	public void validarLogin(String login){
		if(login==null) {
			throw new RegraNegocioException("Login é obrigatorio");
		}
		Optional<Morador> moradores = procurarPorLogin(login);
		if(moradores.isPresent()) {
			throw new RegraNegocioException("Já existe um morador cadastrado com este Login.");
		}
	}
	
	@Override
	public void validarCPF(String cpf) {
		if(cpf==null) {
			throw new RegraNegocioException("CPF é obrigatorio");
		}
		Optional<Morador>moradores = procurarPorCPF(cpf);
		if(moradores.isPresent()) {
			throw new RegraNegocioException("Já existe um morador cadastrado com este CPF.");
		}
	}
	
	public List<Morador> listarMoradores() {
		return (List<Morador>) repository.findAll();
	}
	
	public Optional<Morador> procurarPorId(Long id){
		return repository.findById(id);
	}

	public Optional<Morador> procurarPorLogin(String login){
		return repository.findByLogin(login);
	}
	
	public Optional<Morador> procurarPorCPF(String cpf){
		return repository.findByCPF(cpf);
	}
	
	public void deletarPorId(Long id) {
		repository.deleteById(id);
	}
}
