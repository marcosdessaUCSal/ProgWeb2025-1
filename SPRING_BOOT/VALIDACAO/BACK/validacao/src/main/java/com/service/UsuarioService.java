package com.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.UsuarioDTO;
import com.exception.EmailJaCadastradoException;
import com.exception.ValidacaoEntidadeException;
import com.model.Usuario;
import com.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
public class UsuarioService {
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public void addUsuario(UsuarioDTO dto) {
		Usuario usuario = new Usuario(dto);
		
		// email já cadastrado
		if (usuarioRepository.findByEmail_Email(dto.email).isPresent()) {
			throw new EmailJaCadastradoException(dto.email);
		}
		
		// Conjunto de violações de restrições na construção do email
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		
		if (!violations.isEmpty()) {
		    String erro = violations.stream()
		        .map(v -> v.getMessage())
		        .reduce("", (a, b) -> b);
		    throw new ValidacaoEntidadeException(erro.trim());
		}

		usuarioRepository.save(usuario);
	}
	
	public List<UsuarioDTO> getAll() {
		return usuarioRepository.findAll().stream().map(
				usuario -> new UsuarioDTO(usuario)
				).collect(Collectors.toList());
	}
	
	@Transactional
	public void clear() {
		usuarioRepository.deleteAll();
	}
	
	@Transactional
	public void mock() {
		clear();
		addUsuario(new UsuarioDTO(null, "marcos@email.com"));
		addUsuario(new UsuarioDTO(null, "joao@email.com"));
		addUsuario(new UsuarioDTO(null, "flavio@email.com"));
	}

}
