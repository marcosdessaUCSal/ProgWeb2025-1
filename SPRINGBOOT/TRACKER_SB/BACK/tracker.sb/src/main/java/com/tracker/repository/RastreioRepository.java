package com.tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracker.model.Rastreio;

public interface RastreioRepository extends JpaRepository<Rastreio, Long> {
	
	List<Rastreio> findByCodigo(String codigo);

}
