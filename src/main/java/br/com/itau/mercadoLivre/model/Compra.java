package br.com.itau.mercadoLivre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.itau.mercadoLivre.enums.GatewayPagamento;
import br.com.itau.mercadoLivre.enums.StatusCompra;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;
	
	@Positive
	@NotNull
	@Column(nullable = false)
	private Long quantidade;
		
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusCompra statusCompra;

	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private GatewayPagamento gateway;

	@ManyToOne
	@NotNull
	@Valid	
	private Usuario usuario;
	
	public Compra() { }

	public Compra(@NotNull @Valid Produto produto, @Positive Long quantidade,
			@NotNull @Valid Usuario usuario, GatewayPagamento gateway) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.statusCompra = StatusCompra.INICIADA;
		this.usuario = usuario;
		this.gateway = gateway;
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public StatusCompra getStatusCompra() {
		return statusCompra;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}
	
}
