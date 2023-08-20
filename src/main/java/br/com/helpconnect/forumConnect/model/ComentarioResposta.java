package br.com.helpconnect.forumConnect.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_comentario_resposta")
public class ComentarioResposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@NotNull
	@Column(name="conteudo", length=5000)
	private String conteudo;
	
	@Column(name="gostei")
	private int gostei;
	
	@ManyToOne
	@JoinColumn(name="resposta")
	@JsonIgnoreProperties({"comentarios", "postagem", "usuario"})
	private Resposta resposta;
	
	@ManyToOne
	@JoinColumn(name="usuario")
	@JsonIgnoreProperties({"comentariosRespostas", "postagens", "respostas", "comentarios"})
	private Usuario usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public int getGostei() {
		return gostei;
	}
	public void setGostei(int gostei) {
		this.gostei = gostei;
	}
	public Resposta getResposta() {
		return resposta;
	}
	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
}
