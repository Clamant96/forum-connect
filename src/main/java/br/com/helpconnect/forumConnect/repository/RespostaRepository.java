package br.com.helpconnect.forumConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.helpconnect.forumConnect.model.Resposta;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {

}
