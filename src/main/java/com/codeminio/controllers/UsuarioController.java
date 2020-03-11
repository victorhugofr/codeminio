package com.codeminio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeminio.exceptions.ErroAutenticacao;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.model.Usuario;
import com.codeminio.services.UsuarioService;

@RestController/*Arquitetura REST*/
@RequestMapping(value="/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody Usuario usuario) {
		try {
			Usuario usuarioAutenticado = service.autenticar(usuario.getLogin(), usuario.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);
		}catch(ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/salvar")
	public ResponseEntity salvar(@RequestBody Usuario usuario) {
		try {
			Usuario usuarioSalvo= service.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo,HttpStatus.CREATED);
		}catch(RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
}
