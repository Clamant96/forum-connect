package br.com.helpconnect.forumConnect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpconnect.forumConnect.model.ComentarioResposta;
import br.com.helpconnect.forumConnect.repository.ComentarioRespostaRepository;

@Service
public class ComentarioRespostaService {
	
	@Autowired
	private ComentarioRespostaRepository comentarioRespostaRepository;
	
	public int registraAvaliacao(long id, String status) {
		
		Optional<ComentarioResposta> comentarioRespostaExistente = comentarioRespostaRepository.findById(id);
		
		if(comentarioRespostaExistente.isPresent()) {
			if(status.equals("ok")) { // ADICIONA
				comentarioRespostaExistente.get().setGostei(comentarioRespostaExistente.get().getGostei() + 1);
				
			}else { // SUBTRAI
				comentarioRespostaExistente.get().setGostei(comentarioRespostaExistente.get().getGostei() - 1);
				
			}
			
			comentarioRespostaRepository.save(comentarioRespostaExistente.get());
		
			return comentarioRespostaExistente.get().getGostei();
		}else {
			return comentarioRespostaExistente.get().getGostei();
		}
		
	}
	
}
