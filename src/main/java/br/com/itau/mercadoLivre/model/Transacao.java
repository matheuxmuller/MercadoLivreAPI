import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
package br.com.itau.mercadoLivre.model;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadoLivre.enums.StatusTransacao;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusTransacao status;
	
	@NotNull
	private Long idTransacao;
	
	@NotNull
	private LocalDateTime instante;
	
	@NotNull 
	@Valid
	@ManyToOne
	private Compra compra;

	public Transacao() { }

	public Transacao(@NotNull StatusTransacao status, @NotNull Long idTransacao, @NotNull @Valid Compra compra) {
		this.status = status;
		this.idTransacao = idTransacao;
		this.compra = compra;		
		this.instante = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public StatusTransacao getStatus() {
		return status;
	}

	public Long getIdTransacao() {
		return idTransacao;
	}

	public LocalDateTime getInstante() {
		return instante;
	}

	public Compra getCompra() {
		return compra;
	}
	
	public boolean concluidaComSucesso() {
		return this.status.equals(StatusTransacao.SUCESSO);
	}
		
}
