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
import com.codeminio.model.Morador;
import com.codeminio.model.Usuario;
import com.codeminio.services.impl.MoradorServiceImpl;

@RestController /* Arquitetura REST */
@RequestMapping(value = "/morador")
public class MoradorController {

	@Autowired
	private MoradorServiceImpl service;


	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody Morador morador) {
		try {
			Morador moradorAutenticado = service.autenticar(morador.getLogin(), morador.getSenha());
			return ResponseEntity.ok(moradorAutenticado);
		}catch(ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/* Servico RESTful */
	@GetMapping(value = "/listar", produces = "application/json")
	public ResponseEntity<List<Morador>> index() {

		List<Morador> moradores = service.listarMoradores();

		return new ResponseEntity<List<Morador>>(moradores, HttpStatus.OK);

	}

	@PostMapping("/salvar")
	public ResponseEntity cadastrar(@RequestBody Morador Morador) {
		try {
			Morador moradorSalvo= service.salvarMorador(Morador);
			return new ResponseEntity(moradorSalvo,HttpStatus.CREATED);
		}catch(RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Morador> procurar(@PathVariable(value = "id") Long id) {

		Optional<Morador> morador = service.procurarPorId(id);

		return new ResponseEntity<Morador>(morador.get(), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Morador> atualizar(@PathVariable(value = "id") Long id, @RequestBody Morador morador) {

		Optional<Morador> antigoMorador = service.procurarPorId(id);

		antigoMorador.map((m) -> {
			System.out.println("teste");
			m.setEmail(morador.getEmail());
			m.setNome(morador.getNome());
			m.setSenha(morador.getSenha());
			m.setLogin(morador.getLogin());
			m.setCPF(morador.getCPF()); 
			m.setTelefone(morador.getTelefone());
			m.setApartamento(morador.getApartamento());
			return service.salvarMorador(m);
		});

		return new ResponseEntity<Morador>(antigoMorador.get(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String deletar(@PathVariable("id") Long id) {
		service.deletarPorId(id);

		return "Morador deletado";
	}
}
