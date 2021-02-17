package br.com.itau.mercadoLivre.form;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import br.com.itau.mercadoLivre.model.Categoria;
import br.com.itau.mercadoLivre.repository.CategoriaRepository;
import br.com.itau.mercadoLivre.validation.ValorUnico;

public class CategoriaForm {

	@NotBlank(message = "O nome da categoria é obrigatório")
	@ValorUnico(domainClass = Categoria.class, fieldName = "nome", message = "O nome da categoria precisa ser único")
	private String nome;

	private String nomeCategoriaMae;

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setNomeCategoriaMae(String nomeCategoriaMae) {
		this.nomeCategoriaMae = nomeCategoriaMae;
	}

	public Categoria converter(CategoriaRepository categoriaRepository) {
		Categoria categoria = new Categoria(nome);

		if (nomeCategoriaMae != null) {
			Optional<Categoria> categoriaMae = categoriaRepository.findByNome(nomeCategoriaMae);
			categoria.setCategoriaMae(categoriaMae);
		}
		return categoria;
	}
}
