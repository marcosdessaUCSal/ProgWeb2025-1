package com.exception;

public class ValidacaoEntidadeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ValidacaoEntidadeException(String detalhes) {
        super("Erro de validação: " + detalhes);
    }

}
