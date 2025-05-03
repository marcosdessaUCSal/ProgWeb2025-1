package com.contatos.model;

import java.io.Serializable;

import com.contatos.dto.ContatoDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Contato implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String email;
	
	private boolean marcado;
	
	public Contato() {}

	public Contato(Long id, String nome, String email, boolean marcado) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.marcado = marcado;
	}
	
	public Contato(ContatoDto dto) {
		this.id = dto.id;
		this.nome = dto.nome;
		this.email = dto.email;
		this.marcado = dto.marcado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isMarcado() {
		return marcado;
	}

	public void setMarcado(boolean marcado) {
		this.marcado = marcado;
	}
	

}
