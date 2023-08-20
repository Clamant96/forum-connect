package br.com.helpconnect.forumConnect.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.helpconnect.forumConnect.model.Categoria;
import br.com.helpconnect.forumConnect.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	Optional<List<Postagem>> findByCategoria(Categoria categoria);
	
	List<Postagem> findAllByOrderByIdDesc();
	
}
