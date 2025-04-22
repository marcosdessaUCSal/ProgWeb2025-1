package com.todolist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.todolist.service.DbService;

@Configuration
public class Config {
	
	@Autowired
	private DbService dbService;
	
	@Bean
	public boolean testeInicial() {
		dbService.populaDb();
		return true;
	}

}
