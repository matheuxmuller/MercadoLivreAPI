package br.com.itau.mercadoLivre.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.itau.mercadoLivre.model.Usuario;
import br.com.itau.mercadoLivre.validation.ValorUnico;

public class UsuarioForm {

	@NotNull(message = "o login não pode ser nulo")
	@NotBlank(message = "o login não pode ser em branco")
	@Email(message = "O login precisa ter o formato do email")
	@ValorUnico(domainClass = Usuario.class, fieldName = "login", message = "Só pode existir um usuário com o mesmo email")
	private String login;

	@NotNull(message = "a senha não pode ser nula")
	@NotBlank(message = "a senha não pode ser em branco")
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario converter() {
		String hashSenha = new BCryptPasswordEncoder().encode(senha);
		return new Usuario(login, hashSenha);
	}
}
