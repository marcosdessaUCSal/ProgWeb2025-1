package model;

import java.util.ArrayList;

public class Rastreio {
	
	private ArrayList<Registro> registros = new ArrayList<Registro>();
	private String codigo;
	
	public Rastreio(String codigo) {
		this.codigo = codigo;
	}
	
	public ArrayList<Registro> getRegistros() {
		return this.registros;
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public void addRegistro(Registro reg) {
		this.registros.add(reg);
	}
	
}
