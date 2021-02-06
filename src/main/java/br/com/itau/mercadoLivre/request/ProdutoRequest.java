package br.com.itau.mercadoLivre.request;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import br.com.itau.mercadoLivre.model.Categoria;
import br.com.itau.mercadoLivre.model.Usuario;
import br.com.itau.mercadoLivre.validacao.ExistsId;

public class ProdutoRequest {

	private @Min(0) Long quantidade;
	
	private @Size(max = 1000) String descricao;

	@ExistsId(domainClass = Categoria.class, fieldName = "nome", message = "o id precisa estar cadastrado no sistema")
	private String categoria;

	private LocalDateTime instanteCadastro;

	@ExistsId(domainClass = Usuario.class, fieldName = "id", message = "O id do usuário precisa estar cadastrado no banco")
	private Long idUsuario;

	private CaracteristicasRequest caracteristicasRequest;

	@Deprecated
	public ProdutoRequest() {
	}

	public ProdutoRequest(CaracteristicasRequest caracteristicasRequest, @Min(1) Long quantidade, @Max(1000) String descricao,
			String categoria, LocalDateTime instanteCadastro, Long idUsuario) {
		super();
		this.caracteristicasRequest = caracteristicasRequest;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.instanteCadastro = instanteCadastro;
		this.idUsuario = idUsuario;
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

	public CaracteristicasRequest getCaracteristicasRequest() {
		return caracteristicasRequest;
	}

}
