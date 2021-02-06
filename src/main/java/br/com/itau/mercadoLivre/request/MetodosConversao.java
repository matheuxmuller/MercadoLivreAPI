package br.com.itau.mercadoLivre.request;

import javax.validation.Valid;

import br.com.itau.mercadoLivre.model.Caracteristicas;
import br.com.itau.mercadoLivre.model.Categoria;

public class MetodosConversao {
	
	public static Categoria ConverteRequestParaCategoria(@Valid CategoriaRequest categoriaRequest) {
		return new Categoria(categoriaRequest.getNome(), categoriaRequest.getId());
	}
	
	public static Caracteristicas ConverteRequestParaCaracteristicas(@Valid CaracteristicasRequest caracteristicasRequest) {
		return new Caracteristicas(caracteristicasRequest.getNome(), caracteristicasRequest.getValor(), caracteristicasRequest.getDescricao());
	}

}
