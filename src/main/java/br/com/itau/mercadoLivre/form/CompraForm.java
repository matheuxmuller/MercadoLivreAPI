package br.com.itau.mercadoLivre.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.itau.mercadoLivre.enums.GatewayPagamento;
import br.com.itau.mercadoLivre.model.Compra;
import br.com.itau.mercadoLivre.model.Produto;
import br.com.itau.mercadoLivre.model.Usuario;

public class CompraForm {
	
	@Positive(message = "a quantidade é positiva")
	@NotNull(message = "a quantidade é obrigatória")
	private Long quantidade;
	
	@NotNull(message = "a quantidade é obrigatória")	
	private GatewayPagamento gateway;
	
	public Long getQuantidade() {
		return quantidade;
	}
		
	public GatewayPagamento getGateway() {
		return gateway;
	}

	public Compra converter(Usuario usuario, Produto produto, GatewayPagamento gateway) {		
		return new Compra(produto, quantidade, usuario, gateway);
	}
		
}
