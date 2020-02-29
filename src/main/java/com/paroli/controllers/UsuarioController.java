package com.paroli.controllers;

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

import com.paroli.model.Usuario;
import com.paroli.repositories.UsuarioRepository;

@RestController/*Arquitetura REST*/
@RequestMapping(value="/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/*Servico RESTful*/
	@GetMapping(value = "/", produces="application/json")
	public ResponseEntity init(@RequestParam(value = "nome", required = false)String nome) {
		return new ResponseEntity("Olá REST Spring Boot",HttpStatus.OK);
	}
	
	@GetMapping(value = "/usuarios/{id}", produces="application/json")
	public ResponseEntity <Usuario>consultarUsuario(@PathVariable(value = "id") Long id) {
		Optional<Usuario>usuario = usuarioRepository.findById(id);
		return new ResponseEntity(usuario.get(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/usuarios", produces="application/json")
	public ResponseEntity <List<Usuario>>listarUsuarios() {
		return new ResponseEntity<List<Usuario>>((List<Usuario>) usuarioRepository.findAll(),HttpStatus.OK);
	}
	
	@PostMapping(value="/usuario/cadastrar", produces = "application/json")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
		Usuario usuarioSalvo=usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}
	
	@PutMapping(value="/usuario/atualizar", produces = "application/json")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario){
		Usuario usuarioSalvo=usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deletar/{id}", produces = "application/text")
	public String deletar (@PathVariable("id")Long id) {
		usuarioRepository.deleteById(id);
		return "Usuario deletado";
	}
}
