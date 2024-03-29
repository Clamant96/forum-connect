package br.com.helpconnect.forumConnect.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "arquivo")
public class ConfigProperties {
    
    private String caminho;
    private boolean localhost = true;
    
	public String getCaminho() {
		this.caminho = this.defineUrl();
		
		return this.caminho;
	}
	
	public boolean isLocalhost() {
		return localhost;
	}
	
	private String defineUrl() {
    	
    	String endereco = "";
    	
    	if(this.isLocalhost()) {
    		endereco = "D:/db-img-forum-connect/";
    		
    	}else {
    		endereco = "/home/kevin/aplicacoes/arquivosUpload/";
    		
    	}
    	
    	return endereco;
    }
	
}