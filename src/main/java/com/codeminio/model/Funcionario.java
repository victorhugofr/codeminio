package com.codeminio.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Funcionario extends Usuario {

  @OneToMany(mappedBy = "autor")
  private List<Aviso> avisos;

}
