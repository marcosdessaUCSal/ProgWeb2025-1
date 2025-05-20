package com.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.dto.RegistroDto;
import com.tracker.service.TrackerService;

@RestController
@RequestMapping("/pesquisar")
public class Controller {
	
	@Autowired
	private TrackerService trackerService;
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Object> pesquisarPorCodigo(@PathVariable("codigo") String cod) {
		List<RegistroDto> dtos = trackerService.getRegistrosDtoPorRastreio(cod);
		
		return ResponseEntity.ok().body(dtos);
	}
	
}
