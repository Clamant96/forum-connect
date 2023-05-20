package br.com.helpconnect.forumConnect.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_resposta")
public class Resposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotNull
	@Column(name="conteudo")
	private String conteudo;
	
	@Column(name="gostei")
	private int gostei;
	
	@ManyToOne
	@JoinColumn(name="postagem")
	@JsonIgnoreProperties("respostas")
	private Postagem postagem;
	
	@Column(name="comentarios")
	@OneToMany(mappedBy = "resposta", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("resposta")
	private List<ComentarioResposta> comentarios = new ArrayList<>();
	
	@ManyToOne
	@JsonIgnoreProperties("respostas")
	private Usuario usuario;
	
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
	public Postagem getPostagem() {
		return postagem;
	}
	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}
	public List<ComentarioResposta> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<ComentarioResposta> comentarios) {
		this.comentarios = comentarios;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
