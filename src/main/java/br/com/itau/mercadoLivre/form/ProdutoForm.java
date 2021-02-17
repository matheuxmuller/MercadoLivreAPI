package br.com.itau.mercadoLivre.form;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.itau.mercadoLivre.model.Caracteristica;
import br.com.itau.mercadoLivre.model.Categoria;
import br.com.itau.mercadoLivre.model.Produto;
import br.com.itau.mercadoLivre.model.Usuario;
import br.com.itau.mercadoLivre.repository.CategoriaRepository;

public class ProdutoForm {
	
	@NotBlank(message = "o nome é obrigatório")
	private String nome;

	@NotNull(message = "o valor é obrigatório")
	@Positive(message = "o valor é maior que zero")
	private BigDecimal valor;

	@NotNull(message = "a quantidade é obrigatório")
	@PositiveOrZero(message = "a quantidade é igual ou maior que zero")
	private Long quantidade;

	@Size(min = 3, message = "o produto possui pelo menos três características")
	@Valid
	private List<Caracteristica> caracteristicas;
	
	@NotBlank(message = "a descrição é obrigatória")
	@Size(max = 1000, message = "a descrição tem máximo de 1000 caracteres.")
	private String descricao;

	@NotNull(message = "a categoria é obrigatória")
	private String nomeCategoria;
		
	public String getNome() {
		return nome;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public Produto converter(CategoriaRepository categoriaRepository, Usuario usuario) {
		Optional<Categoria> categoria = categoriaRepository.findByNome(nomeCategoria);

		if (categoria.isEmpty()) {		
			throw new IllegalStateException("A categoria é obrigatória e deve ser cadastrada");
		}
		return new Produto(nome, valor, quantidade, caracteristicas, descricao, categoria.get(), usuario);
	}
}
