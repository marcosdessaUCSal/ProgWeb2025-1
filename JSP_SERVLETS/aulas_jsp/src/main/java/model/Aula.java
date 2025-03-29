package model;

import java.io.Serializable;

public class Aula implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private int codDisciplina;
	private String assunto;
	private int duracao;
	private String data;	// formato yyyy-mm-dd
	private String horario;

	// CONSTRUTORES
	public Aula() {
	}

	public Aula(AulaDto dto) {
		this.id = dto.id.equals("") ? null : Long.parseLong(dto.id);
		this.codDisciplina = dto.codDisciplina.equals("") ? 0 : Integer.parseInt(dto.codDisciplina);
		this.assunto = dto.assunto;
		this.duracao = dto.duracao.equals("") ? 0 : Integer.parseInt(dto.duracao);
		this.data = dto.data;
		this.horario = dto.horario;
	}

	// GETTERS & SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(int codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
}
