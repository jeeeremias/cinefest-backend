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
public class Photo implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String source;

	@Column
	private boolean main;
	
	@ManyToOne
	@JoinColumn(name = "idFilme")
	@JsonIgnore
	private Movie movie;

	public Photo() {
		super();
	}

	public Photo(String source, boolean main, Movie movie) {
		super();
		this.source = source;
		this.main = main;
		this.movie = movie;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public boolean isMain() {
		return main;
	}

	public void setMain(boolean main) {
		this.main = main;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
