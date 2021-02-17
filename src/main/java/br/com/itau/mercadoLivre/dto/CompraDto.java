package br.com.itau.mercadoLivre.dto;

import java.math.BigDecimal;

import br.com.itau.mercadoLivre.enums.GatewayPagamento;
import br.com.itau.mercadoLivre.enums.StatusCompra;
import br.com.itau.mercadoLivre.model.Compra;

public class CompraDto {
	
	private Long id;
	private String nomeProduto;
	private BigDecimal valorProduto;
	private Long quantidade;
	private StatusCompra statusCompra;
	private GatewayPagamento gateway;
	private String urlPagamento;
	
	public CompraDto(Compra compra, String urlPagamento) {
		this.id = compra.getId();
		this.quantidade = compra.getQuantidade();
		this.nomeProduto = compra.getProduto().getNome();
		this.valorProduto = compra.getProduto().getValor();
		this.statusCompra = compra.getStatusCompra();
		this.gateway = compra.getGateway();
		this.urlPagamento = urlPagamento;
	}

	public Long getId() {
		return id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public BigDecimal getValorProduto() {
		return valorProduto;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public StatusCompra getStatusCompra() {
		return statusCompra;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}

	public String getUrlPagamento() {
		return urlPagamento;
	}
	
}
