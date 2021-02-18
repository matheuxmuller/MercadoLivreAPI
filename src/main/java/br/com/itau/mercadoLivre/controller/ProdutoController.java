package br.com.itau.mercadoLivre.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.itau.mercadoLivre.dto.DetalhesProdutoDto;
import br.com.itau.mercadoLivre.dto.ProdutoDto;
import br.com.itau.mercadoLivre.form.ImagensForm;
import br.com.itau.mercadoLivre.form.OpiniaoForm;
import br.com.itau.mercadoLivre.form.PerguntaForm;
import br.com.itau.mercadoLivre.form.ProdutoForm;
import br.com.itau.mercadoLivre.form.Uploader;
import br.com.itau.mercadoLivre.model.Emails;
import br.com.itau.mercadoLivre.model.Opiniao;
import br.com.itau.mercadoLivre.model.Pergunta;
import br.com.itau.mercadoLivre.model.Produto;
import br.com.itau.mercadoLivre.model.Usuario;
import br.com.itau.mercadoLivre.repository.CategoriaRepository;
import br.com.itau.mercadoLivre.repository.OpiniaoRepository;
import br.com.itau.mercadoLivre.repository.PerguntaRepository;
import br.com.itau.mercadoLivre.repository.ProdutoRepository;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private OpiniaoRepository opiniaoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private PerguntaRepository perguntaRepository;

	@Autowired
	private Emails emails;

	@Autowired
	private Uploader uploader;

	@PostMapping
	public void PostProduto(@RequestBody @Valid ProdutoForm produtoForm, @AuthenticationPrincipal Usuario usuario) {
		Produto produto = produtoForm.converter(categoriaRepository, usuario);
		produtoRepository.save(produto);
	}

	@GetMapping
	public List<ProdutoDto> GetAll() {
		List<Produto> produtos = produtoRepository.findAll();
		return ProdutoDto.converter(produtos);
	}

	@PostMapping("/{id}/imagens")
	public void AddImagens(@PathVariable Long id, @Valid ImagensForm imagensForm,
			@AuthenticationPrincipal Usuario usuario) {
		List<String> links = uploader.envia(imagensForm.getImagens());

		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O produto não existe no sistema!");
		}

		if (produto.get().getUsuario().getId() != usuario.getId()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "A imagem só pode ser cadastrada pelo seu usuário original!");
		}
		produto.get().associaImagens(links);
		produtoRepository.save(produto.get());
	}
	
	@PostMapping("/{id}/opiniao")
	public void addOpiniao(@PathVariable Long id, @RequestBody @Valid OpiniaoForm opiniaoForm,  @AuthenticationPrincipal Usuario consumidor) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inexistente!");
		}
		
		Opiniao opiniao = opiniaoForm.converter(produtoRepository, consumidor);
		opiniaoRepository.save(opiniao);
	}
	
	@PostMapping("/{id}/pergunta")
	public void addPergunta(@PathVariable Long id, @RequestBody @Valid PerguntaForm perguntaForm, @AuthenticationPrincipal Usuario interessado) {

		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inexistente!");
		}
				
		Pergunta pergunta = perguntaForm.converter(produtoRepository, interessado);
		perguntaRepository.save(pergunta);	
		emails.novaPergunta(pergunta);		
	}
	
	@GetMapping("/{id}")
	public DetalhesProdutoDto getDetailsProduto(@PathVariable Long id) {

		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inexistente!");
		}
		
		return new DetalhesProdutoDto(produto.get());
	}
}
