package com.pds1.backend_pds1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "COMBUSTIVEL")
public class CombustivelModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private String tipo;

  @JsonIgnore
  @ManyToMany
  @JoinTable(
      name = "COMBUSTIVEL_POSTO",
      joinColumns = @JoinColumn(name = "combustivel_id"),
      inverseJoinColumns = @JoinColumn(name = "posto_id")
  )
  private Set<PostoModel> postos = new HashSet<>();

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getNome() {
    return tipo;
  }

  public void setNome(String nome) {
    this.tipo = nome;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public Set<PostoModel> getPostos() {
    return postos;
  }

  public void setPostos(Set<PostoModel> postos) {
    this.postos = postos;
  }
}
