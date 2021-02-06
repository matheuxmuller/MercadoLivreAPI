package br.com.itau.mercadoLivre.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email
	@NotBlank
	@Column(unique = true, nullable= false)
	private String usuario;
	
	@NotBlank
	@Size(min = 6)
	private String senha;

	public Usuario(Long id, @Email @NotBlank String usuario, @NotBlank @Size(min = 6) String senha,
			LocalDateTime criadoEm) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.criadoEm = criadoEm;
	}

	private LocalDateTime criadoEm = LocalDateTime.now();

	public Usuario() {}

	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public String getUsuario() {
		return usuario;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}
	
}
