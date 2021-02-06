package br.com.itau.mercadoLivre.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_produto")
public class Produto {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private @OneToMany List<Caracteristicas> caracteristicas;
	private Long quantidade;
	private String descricao;
	private String categoria;
	private LocalDateTime instanteCadastro;
	private Long idUsuario;
	
	@Deprecated
	public Produto() {
	}

	public Produto(List<Caracteristicas> caracteristicas, Long quantidade, String descricao, String categoria,
			LocalDateTime instanteCadastro, Long idUsuario) {
		super();
		this.caracteristicas = caracteristicas;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.instanteCadastro = instanteCadastro;
		this.idUsuario = idUsuario;
	}

	public Long getId() {
		return id;
	}

	public List<Caracteristicas> getCaracteristicas() {
		return caracteristicas;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public LocalDateTime getInstanteCadastro() {
		return instanteCadastro;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}
}
