package com.codeminio.services.impl;

import java.util.List;
import java.util.Optional;

import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.model.Aviso;
import com.codeminio.model.Funcionario;
import com.codeminio.repositories.AvisoRepository;
import com.codeminio.repositories.FuncionarioRepository;
import com.codeminio.services.AvisoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvisoServiceImpl implements AvisoService {

  @Autowired
  private AvisoRepository avisoRepository;

  @Autowired
  private FuncionarioRepository funcionarioRepository;

  @Override
  public List<Aviso> index() {
    List<Aviso> avisos = avisoRepository.findAll();

    return avisos;
  };

  @Override
  public Aviso show(Long idAviso) {
    Optional<Aviso> aviso = avisoRepository.findById(idAviso);

    return aviso.orElseThrow(() -> new RegraNegocioException("Aviso " + idAviso + " não encontrado"));
  }

  @Override
  public Aviso store(Long idFuncionario, Aviso aviso) {
    Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);

    return funcionario.map(f -> {
      aviso.setAutor(f);
      return avisoRepository.save(aviso);
    }).orElseThrow(() -> new RegraNegocioException("Funcionario " + idFuncionario + " não encontrado"));

  };

  // @Override
  // public Aviso show(Aviso aviso) {

  // };

  // Aviso update(Aviso aviso) {

  // };

  // Aviso delete(Aviso aviso) {

  // };
}