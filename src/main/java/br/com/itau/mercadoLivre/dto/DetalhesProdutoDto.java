package br.com.itau.mercadoLivre.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import br.com.itau.mercadoLivre.model.OpinioesCalculations;
import br.com.itau.mercadoLivre.model.Produto;

public class DetalhesProdutoDto {

	private String descricao;
	private String nome;
	private BigDecimal valor;
	private List<DetalhesCaracteristicaDto> caracteristicas;
	private List<String> linksImagens;
	private List<String> perguntas;
	private List<Map<String, String>> opinioes;
	private Double mediaNotas;
	private Long total;

	public DetalhesProdutoDto(Produto produto) {
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.caracteristicas = produto.mapCaracteristicas(DetalhesCaracteristicaDto::new);
		this.linksImagens = produto.mapImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());

		OpinioesCalculations calculoOpiniao = produto.getOpinioesCalculations();
			
		this.opinioes = calculoOpiniao.mapOpinioes(opiniao -> {
			return Map.of("titulo", opiniao.getTitulo(),"descricao",opiniao.getDescricao());
		});
		
		this.mediaNotas = calculoOpiniao.media();				
		this.total = calculoOpiniao.total();
	}
		
	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public List<DetalhesCaracteristicaDto> getCaracteristicas() {
		return caracteristicas;
	}

	public List<String> getLinksImagens() {
		return linksImagens;
	}

	public List<String> getPerguntas() {
		return perguntas;
	}

	public List<Map<String, String>> getOpinioes() {
		return opinioes;
	}

	public Double getMediaNotas() {
		return mediaNotas;
	}

	public Long getTotal() {
		return total;
	}
	
}
