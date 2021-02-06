package br.com.itau.mercadoLivre.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.itau.mercadoLivre.model.Categoria;
import br.com.itau.mercadoLivre.validacao.UniqueValue;

public class CategoriaRequest {

	@NotNull
	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "esse nome já existe")
	private String nome;

	private Long id;

	@Deprecated
	public CategoriaRequest() {
	}

	public CategoriaRequest(@NotNull @NotBlank String nome, Long id) {
		super();
		this.nome = nome;
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public Long getId() {
		return id;
	}

}
