package com.codeminio.controllers;

import java.util.List;

import com.codeminio.model.Aviso;
import com.codeminio.services.AvisoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvisoController {

  @Autowired
  private AvisoService service;

  @GetMapping(value = "/aviso")
  public ResponseEntity<List<Aviso>> index() {

    List<Aviso> avisos = service.index();

    return new ResponseEntity<List<Aviso>>(avisos, HttpStatus.OK);
  }

  @GetMapping(value = "/aviso/{idAviso}")
  public ResponseEntity<Aviso> index(@PathVariable(value = "idAviso") Long idAviso) {

    Aviso aviso = service.show(idAviso);

    return new ResponseEntity<Aviso>(aviso, HttpStatus.OK);
  }

  @PostMapping(value = "/funcionario/{idFuncionario}/aviso")
  public ResponseEntity<Aviso> store(@PathVariable(value = "idFuncionario") Long idFuncionario,
      @RequestBody Aviso aviso) {

    Aviso novoAviso = service.store(idFuncionario, aviso);

    return new ResponseEntity<Aviso>(novoAviso, HttpStatus.CREATED);
  }

}