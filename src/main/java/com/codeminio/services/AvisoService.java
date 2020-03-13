package com.codeminio.services;

import java.util.List;

import com.codeminio.model.Aviso;

public interface AvisoService {
  List<Aviso> index();

  Aviso show(Long idAviso);

  List<Aviso> index(Long idFuncionario);

  Aviso show(Long idFuncionario, Long idAviso);

  Aviso store(Long idFuncionario, Aviso aviso);

  Aviso update(Long idFuncionario, Long idAviso, Aviso aviso);

  Aviso delete(Long idFuncionario, Long idAviso);

}