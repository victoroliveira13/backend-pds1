package com.pds1.backend_pds1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "POSTO")
public class PostoModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "distribuidor_id", nullable = false)
  private DistribuidorModel distribuidor;

  @ManyToOne
  @JoinColumn(name = "endereco_id", nullable = false)
  private EnderecoModel endereco;

  @JsonIgnore
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @ManyToMany(mappedBy = "postos", fetch = FetchType.LAZY)
  private Set<CombustivelModel> combustiveis = new HashSet<>();

  @Column(nullable = false)
  private String razaoSocial;

  @Column(nullable = false)
  private String numeroEndereco;

  @Column(nullable = true)
  private String complementoEndereco;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getRazaoSocial() {
    return razaoSocial;
  }

  public void setRazaoSocial(String razaoSocial) {
    this.razaoSocial = razaoSocial;
  }

  public String getNumeroEndereco() {
    return numeroEndereco;
  }

  public void setNumeroEndereco(String numeroEndereco) {
    this.numeroEndereco = numeroEndereco;
  }

  public String getComplementoEndereco() {
    return complementoEndereco;
  }

  public void setComplementoEndereco(String complementoEndereco) {
    this.complementoEndereco = complementoEndereco;
  }

  public DistribuidorModel getDistribuidor() {
    return distribuidor;
  }

  public void setDistribuidor(DistribuidorModel distribuidor) {
    this.distribuidor = distribuidor;
  }

  public EnderecoModel getEndereco() {
    return endereco;
  }

  public void setEndereco(EnderecoModel endereco) {
    this.endereco = endereco;
  }

  public Set<CombustivelModel> getCombustiveis() {
    return combustiveis;
  }

  public void setCombustiveis(Set<CombustivelModel> combustiveis) {
    this.combustiveis = combustiveis;
  }
}
