package br.com.itau.mercadoLivre.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.itau.mercadoLivre.form.RetornoPagSeguroForm;
import br.com.itau.mercadoLivre.model.Compra;
import br.com.itau.mercadoLivre.repository.CompraRepository;
	
@RestController
public class RetornoCompraController {
	
	@Autowired
	private CompraRepository compraRepository;
		
	@PostMapping("/retorno-pagseguro/{id}")
	public void PostRetornoPagSeguro(@PathVariable Long id, @RequestBody @Valid RetornoPagSeguroForm retornoPagSeguroForm) {		
		
		Optional<Compra> compra = compraRepository.findById(id);
		
		if (compra.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inexistente!");
		} 
		
		compra.get().tentativaPagamento(retornoPagSeguroForm);				
	}
	
}
