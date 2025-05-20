package com.tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tracker.model.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Long> {
	
	List<Registro> findByRastreioCodigo(String cod);
	
}
