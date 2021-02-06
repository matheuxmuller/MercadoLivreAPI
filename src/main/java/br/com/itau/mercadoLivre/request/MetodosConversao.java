package br.com.itau.mercadoLivre.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import br.com.itau.mercadoLivre.model.Caracteristicas;
import br.com.itau.mercadoLivre.model.Categoria;
import br.com.itau.mercadoLivre.model.Produto;

public class MetodosConversao {

	public static Categoria ConverteRequestParaCategoria(@Valid CategoriaRequest categoriaRequest) {
		return new Categoria(categoriaRequest.getNome(), categoriaRequest.getId());
	}

	public static Caracteristicas ConverteRequestParaCaracteristicas(
			@Valid CaracteristicasRequest caracteristicasRequest) {
		return new Caracteristicas(caracteristicasRequest.getNome(), caracteristicasRequest.getValor(),
				caracteristicasRequest.getDescricao());
	}

	public static Produto ConverteRequestParaProduto(@Valid ProdutoRequest produtoRequest) {
		List<Caracteristicas> caracteristica = new ArrayList<Caracteristicas>();
		caracteristica.add(ConverteRequestParaCaracteristicas(produtoRequest.getCaracteristicasRequest()));
		return new Produto(caracteristica, produtoRequest.getQuantidade(), produtoRequest.getDescricao(),
				produtoRequest.getCategoria(), LocalDateTime.now(), produtoRequest.getIdUsuario());
	}

}
