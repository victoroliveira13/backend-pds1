package com.pds1.backend_pds1.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "combustivel_posto")
public class CombustivelPostoModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "posto_id", nullable = false)
  private PostoModel posto;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "combustivel_id", nullable = false)
  private CombustivelModel combustivel;

  @Column(nullable = false)
  private BigDecimal preco;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PostoModel getPosto() {
    return posto;
  }

  public void setPosto(PostoModel posto) {
    this.posto = posto;
  }

  public CombustivelModel getCombustivel() {
    return combustivel;
  }

  public void setCombustivel(CombustivelModel combustivel) {
    this.combustivel = combustivel;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }
}
