package br.com.itau.mercadoLivre.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Produto {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@NotNull
	@Column(nullable = false)	
	private BigDecimal valor;
	
	@NotNull
	@Column(nullable = false)		
	private Long quantidade;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "produto_id")
	private List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();
	
	@NotBlank
	@Column(nullable = false, length = 1000)
	private String descricao;
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Categoria categoria;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Usuario usuario;

	private LocalDateTime cadastradoEm = LocalDateTime.now();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private List<ImagemProduto> imagens = new ArrayList<ImagemProduto>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private List<Opiniao> opinioes = new ArrayList<Opiniao>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private List<Pergunta> perguntas = new ArrayList<Pergunta>();
				
	public Produto() { }
	
	public Produto(@NotBlank String nome, @NotNull BigDecimal valor, @NotNull Long quantidade,
			List<Caracteristica> caracteristicas, @NotBlank String descricao, @NotNull Categoria categoria,
			@NotNull Usuario usuario) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
		this.descricao = descricao;
		this.categoria = categoria;
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

	public Long getQuantidade() {
		return quantidade;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public LocalDateTime getCadastradoEm() {
		return cadastradoEm;
	}
 
	public Usuario getUsuario() {
		return usuario;
	}
	
	public List<Opiniao> getOpinioes() {
		return opinioes;
	}
	
	public List<Pergunta> getPerguntas() {
		return perguntas;
	}

	public void associaImagens(List<String> links) {
		List<ImagemProduto> imagens = links.stream()
				.map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toList());
		
		this.imagens.addAll(imagens);
	}

	public <T> List<T> mapCaracteristicas(Function<Caracteristica, T> funcaoMap) {
		return this.caracteristicas.stream().map(funcaoMap).collect(Collectors.toList());
	}

	public <T> List<T> mapImagens(Function<ImagemProduto, T> funcaoMap) {
		return this.imagens.stream().map(funcaoMap).collect(Collectors.toList());
	}

	public <T extends Comparable<T>> List<T> mapPerguntas(Function<Pergunta, T> funcaoMap) {
		return this.perguntas.stream().map(funcaoMap).collect(Collectors.toList());
	}

	public OpinioesCalculations getOpinioesCalculations() {
		return new OpinioesCalculations(this.opinioes);
	}

	public Produto abateEstoque(@Positive Long quantidade) {
		this.quantidade -= quantidade;
		return this;
	}
	
}
