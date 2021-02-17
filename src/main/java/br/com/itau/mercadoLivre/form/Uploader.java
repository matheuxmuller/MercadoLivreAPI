package br.com.itau.mercadoLivre.form;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Uploader {

	public List<String> envia(List<MultipartFile> imagens) {
		return imagens.stream().map(imagem -> "http://inhai.dev/" + imagem.getOriginalFilename())
				.collect(Collectors.toList());
	}
}
