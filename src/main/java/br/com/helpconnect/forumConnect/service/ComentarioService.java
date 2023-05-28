package br.com.helpconnect.forumConnect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpconnect.forumConnect.model.Comentario;
import br.com.helpconnect.forumConnect.repository.ComentarioRepository;

@Service
public class ComentarioService {
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public int registraAvaliacao(long id, String status) {
		
		Optional<Comentario> comentarioExistente = comentarioRepository.findById(id);
		
		if(comentarioExistente.isPresent()) {
			if(status.equals("ok")) { // ADICIONA
				comentarioExistente.get().setGostei(comentarioExistente.get().getGostei() + 1);
				
			}else { // SUBTRAI
				comentarioExistente.get().setGostei(comentarioExistente.get().getGostei() - 1);
				
			}
			
			comentarioRepository.save(comentarioExistente.get());
		
			return comentarioExistente.get().getGostei();
		}else {
			return comentarioExistente.get().getGostei();
		}
		
	}
	
}
