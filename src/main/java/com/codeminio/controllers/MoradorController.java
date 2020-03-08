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

import com.codeminio.model.Morador;
import com.codeminio.repositories.MoradorRepository;

@RestController /* Arquitetura REST */
@RequestMapping(value = "/morador")
public class MoradorController {

	@Autowired
	private MoradorRepository moradorRepository;


	/* Servico RESTful */
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Morador>> index() {

		List<Morador> moradores = (List<Morador>) moradorRepository.findAll();

		return new ResponseEntity<List<Morador>>(moradores, HttpStatus.OK);

	}

	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Morador> store(@RequestBody Morador morador) {

		Morador novoMorador = moradorRepository.save(morador);

		return new ResponseEntity<Morador>(novoMorador, HttpStatus.OK);
	}


	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Morador> show(@PathVariable(value = "id") Long id) {

		Optional<Morador> morador = moradorRepository.findById(id);

		return new ResponseEntity<Morador>(morador.get(), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Morador> atualizar(@PathVariable(value = "id") Long id, @RequestBody Morador morador) {

		Optional<Morador> antigoMorador = moradorRepository.findById(id);

		antigoMorador.map((m) -> {
			System.out.println("teste");
			m.setEmail(morador.getEmail());
			m.setNome(morador.getNome());
			m.setSenha(morador.getSenha());
			m.setLogin(morador.getLogin());
			m.setCpf(morador.getCpf());
			m.setTelefone(morador.getTelefone());
			m.setApartamento(morador.getApartamento());
			return moradorRepository.save(m);
		});

		return new ResponseEntity<Morador>(antigoMorador.get(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String deletar(@PathVariable("id") Long id) {
		moradorRepository.deleteById(id);

		return "Morador deletado";
	}
}
