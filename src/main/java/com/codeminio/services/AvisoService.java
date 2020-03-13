package com.codeminio.services;

import java.util.List;

import com.codeminio.model.Aviso;

public interface AvisoService {
  List<Aviso> index();

  Aviso show(Long idAviso);

  Aviso store(Long idFuncionario, Aviso aviso);

  // Aviso show(Aviso aviso);

  // Aviso update(Aviso aviso);

  // Aviso delete(Aviso aviso);

}