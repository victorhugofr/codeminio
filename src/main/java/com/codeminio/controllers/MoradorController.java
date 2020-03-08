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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeminio.model.Morador;
import com.codeminio.repositories.MoradorRepository;

@RestController/*Arquitetura REST*/
@RequestMapping(value="/morador")
public class MoradorController {

	@Autowired
	private MoradorRepository moradorRepository;
	
	/*Servico RESTful*/
	@GetMapping(value = "/", produces="application/json")
	public ResponseEntity init(@RequestParam(value = "nome", required = false)String nome) {
		return new ResponseEntity("Olá REST Spring Boot",HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces="application/json")
	public ResponseEntity <Morador>consultarUsuario(@PathVariable(value = "id") Long id) {
		Optional<Morador>usuario = moradorRepository.findById(id);
		return new ResponseEntity(usuario.get(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/moradores", produces="application/json")
	public ResponseEntity <List<Morador>>listarUsuarios() {
		return new ResponseEntity<List<Morador>>((List<Morador>) moradorRepository.findAll(),HttpStatus.OK);
	}
	
	@PostMapping(value="/cadastrar", produces = "application/json")
	public ResponseEntity<Morador> cadastrar(@RequestBody Morador usuario){
		Morador usuarioSalvo=moradorRepository.save(usuario);
		return new ResponseEntity<Morador>(usuarioSalvo, HttpStatus.OK);
	}
	
	@PutMapping(value="/atualizar", produces = "application/json")
	public ResponseEntity<Morador> atualizar(@RequestBody Morador usuario){
		Morador usuarioSalvo=moradorRepository.save(usuario);
		return new ResponseEntity<Morador>(usuarioSalvo, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deletar/{id}", produces = "application/text")
	public String deletar (@PathVariable("id")Long id) {
		moradorRepository.deleteById(id);
		return "Morador deletado";
	}
}
