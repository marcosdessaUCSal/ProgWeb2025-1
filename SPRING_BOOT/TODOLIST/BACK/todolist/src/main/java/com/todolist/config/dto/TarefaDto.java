package com.todolist.config.dto;

import com.todolist.model.Tarefa;

public class TarefaDto {
	
	public Long id;
	public String txt;
	public boolean marcado;
	
	public TarefaDto() {}

	public TarefaDto(Long id, String txt, boolean marcado) {
		this.id = id;
		this.txt = txt;
		this.marcado = marcado;
	}
	
	public TarefaDto(Tarefa tarefa) {
		this.id = tarefa.getId();
		this.txt = tarefa.getTxt();
		this.marcado = tarefa.isMarcado();
	}
	
}
