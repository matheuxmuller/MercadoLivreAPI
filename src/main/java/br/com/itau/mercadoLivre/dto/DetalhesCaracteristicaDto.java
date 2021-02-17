package br.com.itau.mercadoLivre.dto;

import br.com.itau.mercadoLivre.model.Caracteristica;

public class DetalhesCaracteristicaDto {

	private String nome;
	private String descricao;

	public DetalhesCaracteristicaDto(Caracteristica caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
		
}
