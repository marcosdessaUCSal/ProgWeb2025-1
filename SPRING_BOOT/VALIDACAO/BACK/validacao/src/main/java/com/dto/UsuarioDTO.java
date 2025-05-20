package com.dto;

import com.model.Usuario;

public class UsuarioDTO {
	
	public Long id;
	public String email;
	
	public UsuarioDTO() {}

	public UsuarioDTO(Long id, String email) {
		this.id = id;
		this.email = email;
	}
	
	public UsuarioDTO(Usuario usr) {
		this.id = usr.getId();
		this.email = usr.getEmail().toString();
	}

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", email=" + email + "]";
	}
	
}
