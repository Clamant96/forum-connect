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

import br.com.helpconnect.forumConnect.model.Postagem;
import br.com.helpconnect.forumConnect.model.Resposta;
import br.com.helpconnect.forumConnect.repository.RespostaRepository;
import br.com.helpconnect.forumConnect.service.RespostaService;

@RestController
@RequestMapping("/resposta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RespostaController {
	
	@Autowired
	private RespostaRepository repository;
	
	@Autowired
	private RespostaService service;
	
	@GetMapping
	public ResponseEntity<List<Resposta>> findAllRespostas() {
		
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Resposta> findByIdResposta(@PathVariable("id") long id) {
		
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/gostei/{id}/{status}")
	public int registrarVisualizacao(@PathVariable("id") long id, @PathVariable("status") String status) {
		
		return service.registraAvaliacao(id, status);
	}
	
	@PostMapping
	public ResponseEntity<Resposta> postResposta(@RequestBody Resposta resposta) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(resposta));
	}
	
	@PutMapping
	public ResponseEntity<Resposta> putResposta(@RequestBody Resposta resposta) {
		
		return ResponseEntity.ok(repository.save(resposta));
	}
	
	@PutMapping("/atualiza/conteudo")
	public boolean putConteudoResposta(@RequestBody Resposta resposta) {
		
		return service.atualizaConteudoPostagem(resposta);
	}
	
	@DeleteMapping("/{id}")
	public void deleteResposta(long id) {
		repository.deleteById(id);
		
	}
	
}
