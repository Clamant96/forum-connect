package br.com.helpconnect.forumConnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpconnect.forumConnect.model.Categoria;
import br.com.helpconnect.forumConnect.model.Postagem;
import br.com.helpconnect.forumConnect.repository.CategoriaRepository;
import br.com.helpconnect.forumConnect.repository.PostagemRepository;

@Service
public class PostagemService {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public boolean registraVisualizacao(long id) {
		
		Optional<Postagem> postagemExistente = postagemRepository.findById(id);
		
		if(postagemExistente.isPresent()) {
			postagemExistente.get().setVisualizacao(postagemExistente.get().getVisualizacao() + 1);
			
			postagemRepository.save(postagemExistente.get());
		
			return true;
		}else {
			return false;
		}
		
	}
	
	public int registraAvaliacao(long id, String status) {
		
		Optional<Postagem> postagemExistente = postagemRepository.findById(id);
		
		if(postagemExistente.isPresent()) {
			if(status.equals("ok")) { // ADICIONA
				postagemExistente.get().setGostei(postagemExistente.get().getGostei() + 1);
				
			}else { // SUBTRAI
				postagemExistente.get().setGostei(postagemExistente.get().getGostei() - 1);
				
			}
			
			postagemRepository.save(postagemExistente.get());
		
			return postagemExistente.get().getGostei();
		}else {
			return postagemExistente.get().getGostei();
		}
		
	}
	
	public boolean atualizaConteudoPostagem(Postagem postagem) {
			
		Optional<Postagem> postagemExistente = postagemRepository.findById(postagem.getId());
		
		if(postagemExistente.isPresent()) {
			postagemExistente.get().setConteudo(postagem.getConteudo());
			
			System.out.println(postagemExistente.get().getConteudo());
			
			postagemRepository.save(postagemExistente.get());
		
			return true;
		}else {
			return false;
		}
		
	}
	
	public List<Postagem> buscaPostagensPorCategoria(long id) {
			
		Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);
		Optional<List<Postagem>> postagemExistente = postagemRepository.findByCategoria(categoriaExistente.get());
		
		return postagemExistente.get();
	}
	
}
