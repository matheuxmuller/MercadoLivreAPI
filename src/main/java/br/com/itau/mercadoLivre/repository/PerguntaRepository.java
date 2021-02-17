package br.com.itau.mercadoLivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.itau.mercadoLivre.model.Pergunta;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

}
