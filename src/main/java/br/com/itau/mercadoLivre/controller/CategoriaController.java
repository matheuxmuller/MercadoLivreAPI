package br.com.itau.mercadoLivre.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.mercadoLivre.model.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

//	@Autowired
//	private CategoriaRepository categoriaRepository;
//
//	@GetMapping
//	public Page<Categoria> listarCategoria(@RequestParam(required = false) String nome,
//			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacaoCat) {
//		if (nome == null) {
//			return categoriaRepository.findAll(paginacaoCat);
//		} else {
//			return categoriaRepository.findNomeContainingIgnoreCase(nome, paginacaoCat);
//		}
//	}
//
//	@PostMapping
//	public ResponseEntity<Categoria> cadastraCategoria(@Valid @RequestBody Categoria categoria) {
//		Optional<Categoria> categoriaDuplicada = categoriaRepository.findByNome(categoria.getNome());
//		if (categoriaDuplicada.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
//		}
//		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esta categoria já existe em nosso sistema");
//	}

	@PostMapping
	public String postMethodName(@RequestBody @Valid Categoria request) {
		Categoria categoria = request.toModel();
		return request.toString();
	}

}
