package dto;

import model.Registro;

public class RegistroDto {
	
	public String data;
	public String cidade;
	public String descr;
	
	
	public RegistroDto(Registro reg) {
		this.data = reg.data;
		this.cidade = reg.cidade;
		this.descr = reg.descr;
	}

}
