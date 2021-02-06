package br.com.itau.mercadoLivre.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.itau.mercadoLivre.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	public Page<Categoria> findByNomeContainingIgnoreCase(String nome, Pageable paginacaoCat);
	public Optional<Categoria>  findByNome(String Nome);
	
}
