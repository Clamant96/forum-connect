package br.com.helpconnect.forumConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.helpconnect.forumConnect.model.ComentarioResposta;
import br.com.helpconnect.forumConnect.repository.ComentarioRespostaRepository;

@RestController
@RequestMapping("/comentario-resposta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComentarioRespostaController {
	
	@Autowired
	private ComentarioRespostaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<ComentarioResposta>> findAllComentarios() {
		
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ComentarioResposta> findByIdComentario(@PathVariable("id") long id) {
		
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<ComentarioResposta> postComentario(@RequestBody ComentarioResposta comentario) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(comentario));
	}
	
	@PutMapping
	public ResponseEntity<ComentarioResposta> putComentario(@RequestBody ComentarioResposta comentario) {
		
		return ResponseEntity.ok(repository.save(comentario));
	}
	
	@DeleteMapping("/{id}")
	public void deleteComentario(long id) {
		repository.deleteById(id);
		
	}
	
}
