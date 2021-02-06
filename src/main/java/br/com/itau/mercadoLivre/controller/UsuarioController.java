package br.com.itau.mercadoLivre.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.itau.mercadoLivre.model.Login;
import br.com.itau.mercadoLivre.model.Usuario;
import br.com.itau.mercadoLivre.repository.UsuarioRepository;
import br.com.itau.mercadoLivre.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
 
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/logar")
	public ResponseEntity<Login> Autentication(@RequestBody Optional<Login> user){
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario){
		Optional<Usuario> usuarioCadastrado = usuarioRepository.findByUsuario(usuario.getUsuario());
		if(!usuarioCadastrado.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(usuarioService.CadastrarUsuario(usuario));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este Usuario já existe em nosso sistema" );
	
	}
	
}
