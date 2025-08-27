package com.pds1.backend_pds1.controller;


import com.pds1.backend_pds1.dtos.AuthenticationDto;
import com.pds1.backend_pds1.dtos.RegisterDto;
import com.pds1.backend_pds1.dtos.response.LoginDto;
import com.pds1.backend_pds1.model.UsuarioModel;
import com.pds1.backend_pds1.repository.UsuarioRepository;
import com.pds1.backend_pds1.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UsuarioRepository usuarioRepository;
  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthenticationDto data) {
    try {
      var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
      var auth = this.authenticationManager.authenticate(usernamePassword);

      var usuario = (UsuarioModel) auth.getPrincipal();
      var token = tokenService.generateToken(usuario);

      return ResponseEntity.ok(new LoginDto(token, usuario.getNome(), usuario.getEmail(), usuario.getRole(), usuario.getId()));

    } catch (org.springframework.security.authentication.BadCredentialsException |
             org.springframework.security.authentication.InternalAuthenticationServiceException ex) {

      Map<String, Object> body = new HashMap<>();
      body.put("timestamp", java.time.LocalDateTime.now().toString());
      body.put("status", 401);
      body.put("error", "Unauthorized");
      body.put("message", "Usuário inexistente ou senha inválida");

      return ResponseEntity.status(401).body(body);
    }
  }


  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterDto data) {
    if (this.usuarioRepository.findByLogin(data.login()) != null) {
      return ResponseEntity.badRequest().body("Usuário já existe.");
    }

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
    UsuarioModel usuario = new UsuarioModel(data.nome(), data.login(), encryptedPassword, data.email(), data.role());

    this.usuarioRepository.save(usuario);
    return ResponseEntity.ok().build();
  }



}
