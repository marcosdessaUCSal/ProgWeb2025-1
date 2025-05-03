package com.contatos.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contatos.dto.ContatoDto;
import com.contatos.service.ContatoService;

@RestController
@RequestMapping("/contatos")
public class Controller {
	
	@Autowired
	private ContatoService contatoService;
	
	@GetMapping("/lista")
	public ResponseEntity<Object> getAll() {
		try {
			List<ContatoDto> dtos = contatoService.getAll();
			return ResponseEntity.ok().body(dtos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Houve um erro inesperado");
		}
	}
	
	@PutMapping("/marcaTudo")
	public ResponseEntity<Object> marcaTudo() {
		try {
			contatoService.marcarTudo(true);
			return ResponseEntity.ok().body("Todos os tópicos desmarcados com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro inesperado");
		}
	}
	
	@PutMapping("/desmarcaTudo")
	public ResponseEntity<Object> desmarcaTudo() {
		try {
			contatoService.marcarTudo(false);
			return ResponseEntity.ok().body("Todos os tópicos desmarcados com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro inesperado");
		}
	}
	
	@DeleteMapping("/removeMarcados")
	public ResponseEntity<Object> removeMarcados() {
		try {
			contatoService.removerMarcados();
			return ResponseEntity.ok().body("Itens removidos com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro inesperado");
		}
	}
	
	@PutMapping("inverteMarcado/{id}")
	public ResponseEntity<Object> marcarOuDesmarcar(@PathVariable("id") Long id) {
		try {
			contatoService.inverterMarcado(id);
			return ResponseEntity.ok().body("Itens removidos com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Houve um erro inesperado");
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> add(@RequestBody ContatoDto dto) {
		try {
			contatoService.addContato(dto);
			return ResponseEntity.ok().body("Item adicionado com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro inesperado");
		}
	}
	
	@PutMapping("/reset")
	public ResponseEntity<Object> reset() {
		try {
			contatoService.reset();
			return ResponseEntity.ok().body("Item adicionado com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro inesperado");
		}
	}
	
}
