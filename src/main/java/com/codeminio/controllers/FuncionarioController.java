package com.codeminio.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeminio.exceptions.ErroAutenticacao;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.model.Funcionario;
import com.codeminio.model.Morador;
import com.codeminio.services.impl.FuncionarioServiceImpl;

@RestController /* Arquitetura REST */
@RequestMapping(value = "/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioServiceImpl service;


	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody Funcionario funcionario) {
		try {
			Funcionario funcionarioAutenticado = service.autenticar(funcionario.getLogin(), funcionario.getSenha());
			return ResponseEntity.ok(funcionarioAutenticado);
		}catch(ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/* Servico RESTful */
	@GetMapping(value = "/listar", produces = "application/json")
	public ResponseEntity<List<Funcionario>> index() {

		List<Funcionario> funcionarios = service.listarFuncionarioes();

		return new ResponseEntity<List<Funcionario>>(funcionarios, HttpStatus.OK);

	}

	@PostMapping("/salvar")
	public ResponseEntity cadastrar(@RequestBody Funcionario Funcionario) {
		try {
			Funcionario funcionarioSalvo= service.salvarFuncionario(Funcionario);
			return new ResponseEntity(funcionarioSalvo,HttpStatus.CREATED);
		}catch(RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Funcionario> procurar(@PathVariable(value = "id") Long id) {

		Optional<Funcionario> Funcionario = service.procurarPorId(id);

		return new ResponseEntity<Funcionario>(Funcionario.get(), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Funcionario> atualizar(@PathVariable(value = "id") Long id, @RequestBody Funcionario funcionario) {

		Optional<Funcionario> antigoFuncionario = service.procurarPorId(id);

		antigoFuncionario.map((m) -> {
			System.out.println("teste");
			m.setEmail(funcionario.getEmail());
			m.setNome(funcionario.getNome());
			m.setSenha(funcionario.getSenha());
			m.setLogin(funcionario.getLogin());
			m.setCPF(funcionario.getCPF()); 
			m.setTelefone(funcionario.getTelefone());
			return service.salvarFuncionario(m);
		});

		return new ResponseEntity<Funcionario>(antigoFuncionario.get(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String deletar(@PathVariable("id") Long id) {
		service.deletarPorId(id);

		return "Funcionario deletado";
	}
}
