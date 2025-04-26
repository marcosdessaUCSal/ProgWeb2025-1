package com.todolist.model;

import java.io.Serializable;

import com.todolist.dto.TarefaDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tarefa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String txt;
	private boolean marcado;
	
	public Tarefa() {}

	public Tarefa(Long id, String txt, boolean marcado) {
		this.id = id;
		this.txt = txt;
		this.marcado = marcado;
	}
	
	public Tarefa(TarefaDto dto) {
		this.id = dto.id;
		this.txt = dto.txt;
		this.marcado = dto.marcado;
	}
	
	// GETTERS & SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public boolean isMarcado() {
		return marcado;
	}

	public void setMarcado(boolean marcado) {
		this.marcado = marcado;
	}
	
	
	
}
