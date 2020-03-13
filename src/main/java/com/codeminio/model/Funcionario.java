package com.codeminio.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Funcionario extends Usuario {

  @JsonBackReference
  @OneToMany(mappedBy = "autor")
  private List<Aviso> avisos;

  public List<Aviso> getAvisos() {
    return this.avisos;
  }

  public void setAvisos(List<Aviso> avisos) {
    this.avisos = avisos;
  }

  public Aviso getAviso(Long idAviso) {
    for (Aviso aviso : this.avisos) {
      if (aviso.getId() == idAviso) {
        return aviso;
      }
    }

    return null;
  }
}
