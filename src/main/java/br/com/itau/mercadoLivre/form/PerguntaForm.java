package br.com.itau.mercadoLivre.form;

import java.util.Optional;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.mercadolivre.model.Pergunta;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.ProdutoRepository;

public class PerguntaForm {
	
	@NotBlank(message = "O título é obrigatório")
	private String titulo;

	@NotBlank(message = "O produto é obrigatório")	
	private String nomeProduto;
	
	public String getTitulo() {
		return titulo;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public Pergunta converter(ProdutoRepository produtoRepository, Usuario interessado) {
		Optional<Produto> produto = produtoRepository.findByNome(nomeProduto);	
		 
		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "produto inexistente!");
		}
		
		return new Pergunta(titulo, interessado, produto.get());
	}
	
}
