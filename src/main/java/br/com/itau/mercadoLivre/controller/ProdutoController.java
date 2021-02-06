package br.com.itau.mercadoLivre.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.mercadoLivre.model.Categoria;
import br.com.itau.mercadoLivre.model.Produto;
import br.com.itau.mercadoLivre.repository.ProdutoRepository;
import br.com.itau.mercadoLivre.request.MetodosConversao;
import br.com.itau.mercadoLivre.request.ProdutoRequest;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EntityManager entity;

	@GetMapping
	public ResponseEntity<List<Produto>> GetAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid ProdutoRequest produtoRequest) {
		try {
			Produto produto = MetodosConversao.ConverteRequestParaProduto(produtoRequest);
			Categoria.persiste(entity, produto.getCaracteristicas());
			produtoRepository.save(produto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw e;
		}

	}
}