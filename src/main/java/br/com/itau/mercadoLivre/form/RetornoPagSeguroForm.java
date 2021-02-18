package br.com.itau.mercadoLivre.form;

import javax.validation.constraints.NotNull;

import br.com.itau.mercadoLivre.enums.StatusRetornoPagSeguro;
import br.com.itau.mercadoLivre.model.Compra;
import br.com.itau.mercadoLivre.model.Transacao;

public class RetornoPagSeguroForm {
	
	@NotNull
	private Long idTransacao;	
	private StatusRetornoPagSeguro status;

	public Long getIdTransacao() {
		return idTransacao;
	}
	public StatusRetornoPagSeguro getStatus() {
		return status;
	}
	public Transacao toTransacao(Compra compra) {
		return new Transacao(status.normaliza(), idTransacao, compra);
	}
	
}
