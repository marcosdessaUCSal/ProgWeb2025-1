package com.model;

import java.io.Serializable;

import com.dto.UsuarioDTO;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Embedded
	@Valid
	private Mail email;

	public Usuario() {
	}

	public Usuario(Long id, Mail email) {
		this.id = id;
		this.email = email;
	}
	
	// Incluindo a entidade a partir do DTO
	public Usuario(UsuarioDTO dto) {
		this.id = dto.id;
		this.email = new Mail(dto.email);
	}

	// GETTERS & SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Mail getEmail() {
		return email;
	}

	public void setEmail(Mail email) {
		this.email = email;
	}

	// TOSTRING
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + "]";
	}

}
