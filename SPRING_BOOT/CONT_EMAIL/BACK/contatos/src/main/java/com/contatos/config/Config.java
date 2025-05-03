package com.contatos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.contatos.service.IniciaService;

@Configuration
public class Config {
	
	@Autowired
	private IniciaService iniciaService;
	
	@Bean
	public boolean iniciar() {
		iniciaService.iniciaDb();
		return true;
	}

}
