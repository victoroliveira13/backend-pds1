package com.pds1.backend_pds1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  @OneToMany(mappedBy = "combustivel", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<CombustivelPostoModel> postos = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "distribuidor_id", nullable = false)
  private DistribuidorModel distribuidor;

  // 1. Adicionado construtor vazio obrigatório para o JPA
  public CombustivelModel() {
  }

  // Getters e Setters corrigidos
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public Set<CombustivelPostoModel> getPostos() {
    return postos;
  }

  public void setPostos(Set<CombustivelPostoModel> postos) {
    this.postos = postos;
  }

  public DistribuidorModel getDistribuidor() {
    return distribuidor;
  }

  public void setDistribuidor(DistribuidorModel distribuidor) {
    this.distribuidor = distribuidor;
  }
}