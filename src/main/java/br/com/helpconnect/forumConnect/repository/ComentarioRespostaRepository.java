package br.com.helpconnect.forumConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.helpconnect.forumConnect.model.ComentarioResposta;

@Repository
public interface ComentarioRespostaRepository extends JpaRepository<ComentarioResposta, Long> {

}
