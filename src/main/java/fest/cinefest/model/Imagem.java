package fest.cinefest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	private Movie movie;

	public Imagem() {
		super();
	}

	public Imagem(String resource, boolean capa, Movie movie) {
		super();
		this.resource = resource;
		this.capa = capa;
		this.movie = movie;
	}
	
	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
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
