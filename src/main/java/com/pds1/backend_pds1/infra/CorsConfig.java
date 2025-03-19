package com.pds1.backend_pds1.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOriginPatterns("*") // Permite todas as origens (ajuste conforme necessário)
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Inclui OPTIONS
        .allowedHeaders("*") // Permite todos os cabeçalhos
        .allowCredentials(true); // Permite cookies ou autenticação baseada em sessão
  }
}
