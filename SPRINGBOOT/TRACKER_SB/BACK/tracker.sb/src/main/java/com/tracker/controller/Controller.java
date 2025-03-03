package com.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.dto.RegistroDto;
import com.tracker.service.TrackerService;

@RestController
public class Controller {
	
	@Autowired
	private TrackerService trackerService;
	
	@PostMapping(value = "/pesquisar")
	public ResponseEntity<Object> pesquisarPorCodigo(@RequestBody String cod) {
		List<RegistroDto> dtos = trackerService.getRegistrosDtoPorRastreio(cod);
		
		return ResponseEntity.ok().body(dtos);
	}

}
