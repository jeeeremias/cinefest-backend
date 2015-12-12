package fest.cinefest.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Imagem implements Serializable {

	@Id
	@GeneratedValue
	private Integer idImagem;
	
	@Column
	private String resource;

	@Column
	private boolean capa;
	
	@ManyToOne
	@JoinColumn(name = "idFilme")
	@JsonIgnore
	private Filme filme;

	public Imagem() {
		super();
	}
	
	public String getResource() {
		return resource;
	}
	
	public void setResource(String resource) {
		this.resource = resource;
	}
	
	public Filme getFilme() {
		return filme;
	}
	
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	public boolean isCapa() {
		return capa;
	}

	public void setCapa(boolean capa) {
		this.capa = capa;
	}

	public Integer getIdImagem() {
		return idImagem;
	}

	public void setIdImagem(Integer idImagem) {
		this.idImagem = idImagem;
	}

}
