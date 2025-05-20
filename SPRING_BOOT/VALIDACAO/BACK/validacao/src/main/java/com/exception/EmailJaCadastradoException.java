package com.exception;

public class EmailJaCadastradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmailJaCadastradoException(String email) {
		super("Já existe usuário com email informado: " + email);
	}

}
