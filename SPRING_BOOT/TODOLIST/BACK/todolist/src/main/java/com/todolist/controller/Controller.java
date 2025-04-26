package com.todolist.controller;

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

import com.todolist.dto.TarefaDto;
import com.todolist.service.TarefaService;

@RestController
@RequestMapping("/todo")
public class Controller {

	@Autowired
	TarefaService tarefaService;

	@GetMapping("/tarefas")
	public ResponseEntity<Object> findAll() {
		try {
			List<TarefaDto> dtos = tarefaService.findAll();
			return ResponseEntity.ok().body(dtos);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro inesperado");
		}
	}

	@PutMapping("/marcaTudo")
	public ResponseEntity<Object> marcaTudo() {
		try {
			tarefaService.marcarTudo(true);
			return ResponseEntity.ok().body("Todos os tópicos marcados com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro inesperado");
		}
	}

	@PutMapping("/desmarcaTudo")
	public ResponseEntity<Object> desmarcaTudo() {
		try {
			tarefaService.marcarTudo(false);
			return ResponseEntity.ok().body("Todos os tópicos desmarcados com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro inesperado");
		}
	}

	@DeleteMapping("/removeMarcados")
	public ResponseEntity<Object> removeMarcados() {
		try {
			tarefaService.removerMarcados();
			return ResponseEntity.ok().body("Itens removidos com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro inesperado");
		}
	}

	@PutMapping("inverteMarcado/{id}")
	public ResponseEntity<Object> marcarOuDesmarcar(@PathVariable("id") Long id) {
		try {
			tarefaService.inverterMarcado(id);
			return ResponseEntity.ok().body("Itens removidos com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro inesperado");
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		try {
			tarefaService.delete(id);
			return ResponseEntity.ok().body("Item removido com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro inesperado");
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> add(@RequestBody TarefaDto dto) {
		try {
			tarefaService.addTarefa(dto);
			return ResponseEntity.ok().body("Item adicionado com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro inesperado");
		}
	}
	
	@PutMapping("/reset")
	public ResponseEntity<Object> reset() {
		try {
			tarefaService.reset();
			return ResponseEntity.ok().body("Item adicionado com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro inesperado");
		}
	}

}
