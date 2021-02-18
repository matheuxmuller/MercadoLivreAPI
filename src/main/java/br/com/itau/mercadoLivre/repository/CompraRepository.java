package br.com.itau.mercadoLivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.itau.mercadoLivre.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{

}
