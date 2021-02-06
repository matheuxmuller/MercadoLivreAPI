package br.com.itau.mercadoLivre.model;

public class Login {
 
	private String usuario;
	private String senha;
	private String token;
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUsuario() {
		return usuario;
	}
}
