package br.com.itau.mercadoLivre.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.itau.mercadoLivre.model.Usuario;
import br.com.itau.mercadoLivre.repository.UsuarioRepository;

@Service 
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
		
		if (usuario.isPresent()) {
			return usuario.get(); 
		}
		throw new UsernameNotFoundException("Dados inválidos!");
	}
}
