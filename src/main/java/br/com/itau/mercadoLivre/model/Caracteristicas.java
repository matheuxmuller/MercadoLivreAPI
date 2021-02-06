package br.com.itau.mercadoLivre.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_caracteristicas")
public class Caracteristicas {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private String nome;
	private BigDecimal valor;
	private String descricao;

	public Caracteristicas(String nome, BigDecimal valor, String descricao) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

}
