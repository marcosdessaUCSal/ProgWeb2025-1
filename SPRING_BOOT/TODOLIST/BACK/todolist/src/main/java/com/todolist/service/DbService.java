package com.todolist.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.model.Tarefa;
import com.todolist.repository.TarefaRepository;

import jakarta.transaction.Transactional;

@Service
public class DbService {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Transactional
	public void populaDb() {
		Tarefa t1 = new Tarefa(null, "Tomar banho", false);
		Tarefa t2 = new Tarefa(null, "Escovar os dentes", false);
		Tarefa t3 = new Tarefa(null, "Lavar os pratos", false);
		Tarefa t4 = new Tarefa(null, "Varrer a sala", false);
		
		tarefaRepository.saveAll(Arrays.asList(t1, t2, t3, t4));
	}

}
