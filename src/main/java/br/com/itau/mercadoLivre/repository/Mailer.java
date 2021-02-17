package br.com.itau.mercadoLivre.repository;

import javax.validation.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public interface Mailer {
	void send(@NotBlank String body, @NotBlank String subject, @NotBlank String nameFrom, @NotBlank @Email String from, @NotBlank @Email String to);
}
