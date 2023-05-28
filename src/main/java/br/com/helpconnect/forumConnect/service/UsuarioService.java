package br.com.helpconnect.forumConnect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpconnect.forumConnect.model.Usuario;
import br.com.helpconnect.forumConnect.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public int registraVisualizacao(long id) {
		
		Optional<Usuario> postagemExistente = usuarioRepository.findById(id);
		
		return postagemExistente.get().getPostagens().size();
	}
	
}
