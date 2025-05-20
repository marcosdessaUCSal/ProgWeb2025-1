package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UsuarioDTO;
import com.exception.EmailJaCadastradoException;
import com.exception.ResponseDeErros;
import com.exception.ValidacaoEntidadeException;
import com.model.Usuario;
import com.service.UsuarioService;

@RestController
@RequestMapping("/validacao")
public class Controller {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/add")
	public ResponseEntity<Object> addUsuario(@RequestBody UsuarioDTO dto) {
		try {
			usuarioService.addUsuario(dto);
			return ResponseEntity.ok().body("Salvo com sucesso");
		} catch (ValidacaoEntidadeException ex) {
			return ResponseEntity.badRequest().body(new ResponseDeErros("Erro de validação", ex.getMessage()));
		} catch (EmailJaCadastradoException ex) {
			return ResponseEntity.unprocessableEntity()
					.body(new ResponseDeErros("Regra de negocio violada", ex.getMessage()));
		} catch (Exception ex) {
			return ResponseEntity.internalServerError().body(new ResponseDeErros("Erro interno", ex.getMessage()));
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<Object> getAll() {
		try {
			List<UsuarioDTO> lista = usuarioService.getAll();
			return ResponseEntity.ok().body(lista);
		} catch (Exception ex) {
			return ResponseEntity.internalServerError().body(new ResponseDeErros("Erro interno", ex.getMessage()));
		}
	}

	@DeleteMapping("/clear")
	public ResponseEntity<Object> removeAll() {
		try {
			usuarioService.clear();
			return ResponseEntity.ok().body("Dados removidos com sucesso");
		} catch (Exception ex) {
			return ResponseEntity.internalServerError().body(new ResponseDeErros("Erro interno", ex.getMessage()));
		}
	}

	@PutMapping("/mock")
	public ResponseEntity<Object> mock() {
		try {
			usuarioService.mock();
			return ResponseEntity.ok().body("Dados mockados com sucesso");
		} catch (Exception ex) {
			return ResponseEntity.internalServerError().body(new ResponseDeErros("Erro interno", ex.getMessage()));
		}
	}

}
