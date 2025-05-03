package com.contatos.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contatos.model.Contato;
import com.contatos.repository.ContatoRepository;

import jakarta.transaction.Transactional;

@Service
public class IniciaService {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Transactional
	public void iniciaDb() {
		Contato c1 = new Contato(null, "Alan Turing", "turing@email.com", false);
		Contato c2 = new Contato(null, "Linus Torvalds", "linus@email.com", false);
		Contato c3 = new Contato(null, "John von Neumann", "neumann@email.com", false);
		
		contatoRepository.saveAll(Arrays.asList(c1, c2, c3));
	}
	

}
