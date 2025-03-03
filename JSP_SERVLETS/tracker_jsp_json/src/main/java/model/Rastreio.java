package model;

import java.util.ArrayList;

import dto.RegistroDto;

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
	
	public ArrayList<RegistroDto> getDtos() {
		ArrayList<RegistroDto> regs = new ArrayList<RegistroDto>();
		for (Registro r : this.registros) {
			regs.add(new RegistroDto(r));
		}
		return regs;
	}
	
}
