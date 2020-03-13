package com.codeminio.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Aviso {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @CreationTimestamp
  private LocalDateTime dataCriacao;

  @UpdateTimestamp
  private LocalDateTime dataAtualizacao;

  @Column(columnDefinition = "TEXT")
  private String texto;

  @ManyToOne(optional = false)
  @JoinColumn(name = "id_funcionario", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Funcionario autor;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDataCriacao() {
    return this.dataCriacao.toString();
  }

  public void setDataCriacao(LocalDateTime dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public String getDataAtualizacao() {
    return this.dataAtualizacao.toString();
  }

  public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
    this.dataAtualizacao = dataAtualizacao;
  }

  public String getTexto() {
    return this.texto;
  }

  public void setTexto(String aviso) {
    this.texto = aviso;
  }

  public Funcionario getAutor() {
    return this.autor;
  }

  public void setAutor(Funcionario autor) {
    this.autor = autor;
  }
}