package com.contatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contatos.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
	void deleteByMarcadoTrue();
}
