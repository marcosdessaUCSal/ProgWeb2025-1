package com.tracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tracker.service.IniciaService;

@Configuration
public class Config {
	
	@Autowired
	private IniciaService iniciaService;
	
	@Bean
	public boolean iniciar() {
		this.iniciaService.populaDb();
		return true;
	}

}
