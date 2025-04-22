package com.todolist.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.todolist.config.dto.TarefaDto;
import com.todolist.model.Tarefa;
import com.todolist.repository.TarefaRepository;

import jakarta.transaction.Transactional;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private DbService dbService;

	public List<TarefaDto> findAll() {
		List<Tarefa> listaTarefas = tarefaRepository.findAll();
		return listaTarefas.stream().map(
			tarefa -> new TarefaDto(tarefa)
		).collect(Collectors.toList());
	}
	
	// Marca (ou desmarca) tudo
	@Transactional
	public void marcarTudo(boolean marcado) {
		List<Tarefa> listaTarefas = tarefaRepository.findAll();
		for (Tarefa t: listaTarefas) {
			t.setMarcado(marcado);
		}
		tarefaRepository.saveAll(listaTarefas);
	}
	
	@Transactional
	public void removerMarcados() {
		tarefaRepository.deleteByMarcadoTrue();
	}
	
	public void inverterMarcado(Long id) {
		Optional<Tarefa> optional = tarefaRepository.findById(id);
		if (optional.isPresent()) {
			Tarefa tarefa = optional.get();
			tarefa.setMarcado(!tarefa.isMarcado());
			tarefaRepository.save(tarefa);
		}
	}
	
	public void delete(Long id) {
		Optional<Tarefa> optional = tarefaRepository.findById(id);
		if (optional.isPresent()) {
			tarefaRepository.deleteById(id);
		}
	}
	
	public void reset() {
		tarefaRepository.deleteAll();
		dbService.populaDb();
	}
	
	public void addTarefa(TarefaDto dto) {
		tarefaRepository.save(new Tarefa(dto));
	}
	
}
