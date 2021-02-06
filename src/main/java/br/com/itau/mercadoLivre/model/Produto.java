package br.com.itau.mercadoLivre.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@Min(0)
	@Column(nullable= false)
	private BigDecimal valor;//rever
	
	@Min(0)
	@Column(nullable= false)
	private String quantidade;//rever
	
	@Size(min = 3)
	private HashMap<String, Integer> caracteristicas = new HashMap<>();
	
	@NotBlank
	@Column(nullable = false)
	private String descricao;
	
	@ManyToOne
	private Categoria categoria;
	
	@Column(nullable = false)
	private LocalDateTime dataCriacao;
	
	@ManyToOne
	private Usuario usuario;

	public Produto() {}

	public Produto(Long id, @NotBlank String nome, @Min(0) BigDecimal valor, @Min(0) String quantidade,
			@Size(min = 3) HashMap<String, Integer> caracteristicas, @NotBlank String descricao, Categoria categoria,
			LocalDateTime dataCriacao, Usuario usuario) {
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
		this.descricao = descricao;
		this.categoria = categoria;
		this.dataCriacao = dataCriacao;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public HashMap<String, Integer> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	
	
	
	
	
	
	
}
