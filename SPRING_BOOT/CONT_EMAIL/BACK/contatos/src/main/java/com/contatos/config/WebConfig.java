package com.contatos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Aplica a todas as rotas
                .allowedOriginPatterns("http://127.0.0.1:*") // Permite o localhost
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permite os métodos GET, POST, PUT, DELETE
                .allowedHeaders("*") // Permite todos os headers
                .allowCredentials(true); // Permite envio de cookies/autenticação se necessário
			}
			
		};
	}

}
