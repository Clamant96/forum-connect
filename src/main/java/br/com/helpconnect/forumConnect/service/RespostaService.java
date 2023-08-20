package br.com.helpconnect.forumConnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpconnect.forumConnect.model.Postagem;
import br.com.helpconnect.forumConnect.model.Resposta;
import br.com.helpconnect.forumConnect.repository.PostagemRepository;
import br.com.helpconnect.forumConnect.repository.RespostaRepository;

@Service
public class RespostaService {
	
	@Autowired
	private RespostaRepository respostaRepository;
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	public int registraAvaliacao(long id, String status) {
		
		Optional<Resposta> respostaExistente = respostaRepository.findById(id);
		
		if(respostaExistente.isPresent()) {
			if(status.equals("ok")) { // ADICIONA
				respostaExistente.get().setGostei(respostaExistente.get().getGostei() + 1);
				
			}else { // SUBTRAI
				respostaExistente.get().setGostei(respostaExistente.get().getGostei() - 1);
				
			}
			
			respostaRepository.save(respostaExistente.get());
		
			return respostaExistente.get().getGostei();
		}else {
			return respostaExistente.get().getGostei();
		}
		
	}
	
	public boolean atualizaConteudoPostagem(Resposta resposta) {
		
		Optional<Resposta> respostaExistente = respostaRepository.findById(resposta.getId());
		
		if(respostaExistente.isPresent()) {
			respostaExistente.get().setConteudo(resposta.getConteudo());
			
			respostaRepository.save(respostaExistente.get());
		
			return true;
		}else {
			return false;
		}
		
	}
	
	public List<Resposta> buscaRespostasDaPostagem(long id) {
		Optional<Postagem> postagemExistente = postagemRepository.findById(id);
		
		return postagemExistente.get().getRespostas();
	}

}
