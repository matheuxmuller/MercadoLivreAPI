package br.com.itau.mercadoLivre.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.mercadoLivre.enums.GatewayPagamento;
import br.com.zup.mercadoLivre.model.Compra;
import br.com.zup.mercadoLivre.model.Produto;
import br.com.zup.mercadoLivre.model.Usuario;

public class CompraForm {
	
	@Positive(message = "a quantidade é po sitiva")
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
