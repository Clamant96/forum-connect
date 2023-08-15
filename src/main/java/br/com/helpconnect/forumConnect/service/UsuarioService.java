package br.com.helpconnect.forumConnect.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.helpconnect.forumConnect.model.Usuario;
import br.com.helpconnect.forumConnect.model.UsuarioLogin;
import br.com.helpconnect.forumConnect.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public int qtdPostagensIdUsuario(long id) {
		
		Optional<Usuario> postagemExistente = usuarioRepository.findById(id);
		
		return postagemExistente.get().getPostagens().size();
	}
	
	public Optional<Usuario> CadastrarUsuario(Usuario usuario) {	
		
		/* CONDICAO PARA INPEDIR A CRIACAO DE UM USUARIO DUPLICADO DENTRO DA APLICACAO */
		if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent() && usuario.getId() == 0) {
			return null;
			
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		/* REGISTRA O USUARIO NA BASE DE DADOS */
		usuarioRepository.save(usuario);
		
		return Optional.of(usuarioRepository.save(usuario));
	}

	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = usuarioRepository.findByEmail(user.get().getEmail());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

				String auth = user.get().getEmail() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);	
				user.get().setId(usuario.get().getId());
				user.get().setNome(usuario.get().getNome());
				user.get().setImg(usuario.get().getImg());
				user.get().setSenha(usuario.get().getSenha());
				user.get().setEmail(usuario.get().getEmail());
				
				return user;

			}
		}
		return null;
	}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return Optional.of(usuarioRepository.save(usuario));
	}
	
}
