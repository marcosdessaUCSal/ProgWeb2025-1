package com.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Mail {

	@Email(message = "e-mail inválido.")
	@NotBlank(message = "e-mail não pode estar vazio.")
	private String email;

	public Mail() {
	}

	public Mail(String email) {
		this.email = email;
	}

	// GETTERS & SETTERS
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// TOSTRING
	@Override
	public String toString() {
		return this.email;
	}

}
