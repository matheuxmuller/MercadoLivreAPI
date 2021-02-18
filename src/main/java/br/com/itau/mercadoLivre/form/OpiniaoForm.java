package br.com.itau.mercadoLivre.form;

import java.util.Optional;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.itau.mercadoLivre.model.Opiniao;
import br.com.itau.mercadoLivre.model.Produto;
import br.com.itau.mercadoLivre.model.Usuario;
import br.com.itau.mercadoLivre.repository.ProdutoRepository;

public class OpiniaoForm {

	@NotNull(message = "a nota é obrigatória e no mínimo 1 e no máximo 5")
	@Min(1)
	@Max(5)
	private Long notas;

	@NotBlank(message = "o título é obrigatório")
	private String titulo;
	
	@NotBlank(message = "a descrição é obrigatória")
	@Size(max = 500, message = "a descrição tem no máximo 500 caracteres")
	private String descricao;

	@NotBlank(message = "o nome do produto é obrigatório")	
	private String nomeProduto;
		
	public Long getNotas() {
		return notas;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public String getNomeProduto() {
		return nomeProduto;
	}

	public Opiniao converter(ProdutoRepository produtoRepository, Usuario usuario) {
		Optional<Produto> produto = produtoRepository.findByNome(nomeProduto);
		
		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "produto inexistente!");
		}
		return new Opiniao(notas, titulo, descricao, usuario, produto.get());
	}
	
}

