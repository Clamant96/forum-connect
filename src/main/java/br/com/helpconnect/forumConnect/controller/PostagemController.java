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
import br.com.helpconnect.forumConnect.repository.PostagemRepository;
import br.com.helpconnect.forumConnect.service.PostagemService;

@RestController
@RequestMapping("/postagem")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@Autowired
	private PostagemService service;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> findAllPostagens() {
		
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Postagem> findByIdPostagem(@PathVariable("id") long id) {
		
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/visualizacao/{id}")
	public boolean registrarVisualizacao(@PathVariable("id") long id) {
		
		return service.registraVisualizacao(id);
	}
	
	@GetMapping("/gostei/{id}/{status}")
	public int registrarVisualizacao(@PathVariable("id") long id, @PathVariable("status") String status) {
		
		return service.registraAvaliacao(id, status);
	}
	
	@GetMapping("/by/categoria/{id}")
	public List<Postagem> retornaPostagensPorCategoria(@PathVariable("id") long id) {
		
		return service.buscaPostagensPorCategoria(id);
	}
	
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> putPostagem(@RequestBody Postagem postagem) {
		
		return ResponseEntity.ok(repository.save(postagem));
	}
	
	@PutMapping("/atualiza/conteudo")
	public boolean  putConteudoPostagem(@RequestBody Postagem postagem) {
		
		return service.atualizaConteudoPostagem(postagem);
	}
	
	@DeleteMapping("/{id}")
	public void deletePostagem(long id) {
		repository.deleteById(id);
		
	}
	
}
