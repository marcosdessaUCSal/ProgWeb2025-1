package com.tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Registro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "rastreio_id", nullable = false)
	private Rastreio rastreio;

	private Integer indice;

	private String data;

	private String cidade;

	private String descr;

	public Registro() {
	}

	// contrutor
	public Registro(Long id, Integer indice, String data, String cidade, String descr) {
		this.indice = indice;
		this.data = data;
		this.cidade = cidade;
		this.descr = descr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Rastreio getRastreio() {
		return rastreio;
	}

	public void setRastreio(Rastreio rastreio) {
		this.rastreio = rastreio;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

}
