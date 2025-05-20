package com.tracker.dto;

import com.tracker.model.Registro;

public class RegistroDto {
	
	public String data;
	public String cidade;
	public String descr;
	
	
	public RegistroDto(Registro reg) {
		this.data = reg.getData();
		this.cidade = reg.getCidade();
		this.descr = reg.getDescr();
	}


	@Override
	public String toString() {
		return "RegistroDto [data=" + data + ", cidade=" + cidade + ", descr=" + descr + "]";
	}
	
	

}
