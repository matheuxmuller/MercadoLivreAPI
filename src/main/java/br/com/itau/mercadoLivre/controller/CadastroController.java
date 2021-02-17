package br.com.itau.mercadoLivre.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.mercadoLivre.dto.UsuarioDto;
import br.com.itau.mercadoLivre.form.UsuarioForm;
import br.com.itau.mercadoLivre.model.Usuario;
import br.com.itau.mercadoLivre.repository.UsuarioRepository;

@RestController
@RequestMapping ("/cadastrar")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class CadastroController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public void PostUsuario(@RequestBody @Valid UsuarioForm usuarioForm) {
		Usuario usuario = usuarioForm.converter();			
		usuarioRepository.save(usuario);		
	}

	@GetMapping
	public List<UsuarioDto> GetAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return UsuarioDto.converter(usuarios);
	}
}
