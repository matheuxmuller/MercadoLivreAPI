package br.com.itau.mercadoLivre.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(unique = true)
	private String nome;
	
	@ManyToOne
	private Categoria categoriaMae;
	
	@OneToMany(mappedBy = "categoriaMae" )
	private List<Categoria> categoriaFilha;

	public Categoria() {}

	public Categoria(Long id, @NotBlank String nome, Categoria categoriaMae, List<Categoria> categoriaFilha) {
		this.id = id;
		this.nome = nome;
		this.categoriaMae = categoriaMae;
		this.categoriaFilha = categoriaFilha;
	}

	public Categoria(@NotBlank String nome2) {}

	public Categoria getCategoriaMae() {
		return categoriaMae;
	}

	public void setCategoriaMae(Categoria categoriaMae) {
		this.categoriaMae = categoriaMae;
	}

	public List<Categoria> getCategoriaFilha() {
		return categoriaFilha;
	}

	public void setCategoriaFilha(List<Categoria> categoriaFilha) {
		this.categoriaFilha = categoriaFilha;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", categoriaMae=" + categoriaMae + ", categoriaFilha="
				+ categoriaFilha + "]";
	}

	public Categoria toModel() {
		return new Categoria(nome);
	}
	
}
