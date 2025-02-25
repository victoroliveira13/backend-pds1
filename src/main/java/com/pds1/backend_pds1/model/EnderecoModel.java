package com.pds1.backend_pds1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ENDERECO")
public class EnderecoModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToMany(mappedBy = "endereco", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<PostoModel> postos = new HashSet<>();

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToMany(mappedBy = "endereco", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<UsuarioModel> usuarios = new HashSet<>();

  @Column(nullable = false)
  private String cep;

  @Column(nullable = false)
  private String logradouro;

  @Column(nullable = false)
  private String bairro;

  @Column(nullable = false)
  private String cidade;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getLogradouro() {
    return logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public Set<PostoModel> getPostos() {
    return postos;
  }

  public void setPostos(Set<PostoModel> postos) {
    this.postos = postos;
  }

  public Set<UsuarioModel> getUsuarios() {
    return usuarios;
  }

  public void setUsuarios(Set<UsuarioModel> usuarios) {
    this.usuarios = usuarios;
  }
}
