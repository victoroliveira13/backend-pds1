package com.pds1.backend_pds1.model;

import com.pds1.backend_pds1.roles.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "USUARIO")
public class UsuarioModel implements UserDetails {
  private static final long serialVersionUID = 1L;

  public UsuarioModel() {}

  public UsuarioModel(String nome, String login, String encryptedPassword, String email, UserRole role) {
    this.nome = nome;
    this.login = login;
    this.senha = encryptedPassword;
    this.email = email;
    this.role = role;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @NotBlank(message = "Nome é obrigatório")
  @Column(nullable = false)
  private String nome;

  @ManyToOne
  @JoinColumn(name = "endereco_id", nullable = true)
  private EnderecoModel endereco;

  @NotBlank(message = "Login é obrigatório")
  @Column(nullable = false, unique = true)
  private String login;

  @NotBlank(message = "Senha é obrigatória")
  @Column(nullable = false)
  private String senha;

  @NotBlank(message = "Email é obrigatório")
  @Email(message = "Email inválido")
  @Column(nullable = false, unique = true)
  private String email;

  private UserRole role;


  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLogin() {
    return this.login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if(this.role == UserRole.ADMIN)
      return List.of(
          new SimpleGrantedAuthority("ROLE_ADMIN"),
          new SimpleGrantedAuthority("ROLE_USER")
      );
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getPassword() {
    return senha;
  }

  @Override
  public String getUsername() {
    return login;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
