package br.com.itau.mercadoLivre.dto;

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

import br.com.itau.mercadoLivre.dto.ProdutoDto;
import br.com.itau.mercadoLivre.form.ImagensForm;
import br.com.itau.mercadoLivre.form.ProdutoForm;
import br.com.itau.mercadoLivre.form.Uploader;
import br.com.itau.mercadoLivre.model.Produto;
import br.com.itau.mercadoLivre.model.Usuario;
import br.com.itau.mercadoLivre.repository.CategoriaRepository;
import br.com.itau.mercadoLivre.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

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
}
