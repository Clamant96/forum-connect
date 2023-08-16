package br.com.helpconnect.forumConnect.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
	
	public String decodeBase64(String dado) {
		
		byte[] decoded = Base64.decodeBase64(dado);
		String authHeader = new String(decoded);
		
		byte[] decodedDado = Base64.decodeBase64(authHeader);
		String authHeaderDado = new String(decodedDado);
		
		return authHeaderDado;
	}
	
}
