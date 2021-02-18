package br.com.itau.mercadoLivre.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.itau.mercadoLivre.dto.CompraDto;
import br.com.itau.mercadoLivre.enums.GatewayPagamento;
import br.com.itau.mercadoLivre.form.CompraForm;
import br.com.itau.mercadoLivre.model.Compra;
import br.com.itau.mercadoLivre.model.Produto;
import br.com.itau.mercadoLivre.model.Usuario;
import br.com.itau.mercadoLivre.repository.CompraRepository;
import br.com.itau.mercadoLivre.repository.ProdutoRepository;

@RestController
@RequestMapping("/compra")
public class CompraController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CompraRepository compraRepository;
		
	@PostMapping("/{id}")
	public void PostCompra(@PathVariable Long id,@RequestBody @Valid CompraForm compraForm, @AuthenticationPrincipal Usuario usuario, UriComponentsBuilder uriBuilder) {
			 
		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inexistente!");
		} 
		
		GatewayPagamento gateway = compraForm.getGateway();		
 		Compra compra = compraForm.converter(usuario, produto.get(), gateway);
									 		
		Long quantidade = compra.getQuantidade();		
		Produto quantidadeProdutoAlterada = produto.get().abateEstoque(quantidade);		
		produtoRepository.save(quantidadeProdutoAlterada);
		compraRepository.save(compra);				
		
		if (gateway.equals(GatewayPagamento.PAYPAL)) {
			String urlRetornoPaypal = uriBuilder.path("/retorno-paypal/{id}").buildAndExpand(compra.getId()).toString();
			System.out.println("paypal.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal);
		} else {
			String urlRetornoPagSeguro = uriBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(compra.getId()).toString();
			System.out.println("pagseguro.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPagSeguro);			
		}
		
		if (produto.get().getQuantidade() < compra.getQuantidade()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "quantidade de estoque insuficiente.");
		}
		
		if (compra.getQuantidade() <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "a quantidade a ser comprada deve ser maior que zero.");			
		}
		
		if (produto.get().getQuantidade() == 0 && compra.getQuantidade() > 1) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "estoque vazio.");						
		} 
				
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompraDto> GetById(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
		Optional<Compra> compra = compraRepository.findById(id);

		if (compra.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inexistente!");
		} 
			
		if (compra.get().getGateway() == GatewayPagamento.PAYPAL) {
			return ResponseEntity.ok(new CompraDto(compra.get(), "paypal.com/" + compra.get().getId() + "?redirectUrl=http://localhost:8080/retorno-paypal/" + compra.get().getId()));
		}
		
		if (compra.get().getGateway() == GatewayPagamento.PAGSEGURO) {
			return ResponseEntity.ok(new CompraDto(compra.get(), "pagseguro.com/" + compra.get().getId() + "?redirectUrl=http://localhost:8080/retorno-pagseguro/" + compra.get().getId()));
		}
				
		return ResponseEntity.ok(new CompraDto(compra.get(), ""));
	}
				
}
