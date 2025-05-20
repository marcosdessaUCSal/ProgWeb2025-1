package com.exception;

import java.time.LocalDateTime;

public class ResponseDeErros {
	
	public String titulo;
    public String detalhe;
    public LocalDateTime timestamp;

    public ResponseDeErros(String titulo, String detalhe) {
        this.titulo = titulo;
        this.detalhe = detalhe;
        this.timestamp = LocalDateTime.now();
    }
}
