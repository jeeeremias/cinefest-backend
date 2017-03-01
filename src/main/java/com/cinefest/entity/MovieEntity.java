package com.cinefest.entity;

import com.cinefest.util.enumeration.MovieTypeEnum;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MovieEntity implements Serializable {
	
	@Id
	private Long id;
	
	@Column
	private MovieTypeEnum type;
	
	@Column
	private String name;
	
	@Column
	private String city;
	
	@Column
	private String state;
	
	@Column
	private LocalDate incomeDate;
	
	@Column
	private String genre;
	
	@Column
	private String runtime;
	
	@Column
	private LocalDateTime screeningDateTime;
	
	@Column
	private String director;
	
	@Column
	private String shortSynopsis;
	
	@Column(length = 5000)
	private String fullSynopsis;
	
	@Column(length = 5000)
	private String directorBiography;
	
	@Column
	private String directorEmail;
	
	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PhotoEntity> photos;

	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<VoteEntity> votes;

	public MovieEntity() {
		super();
	}

	public MovieEntity(Long id, MovieTypeEnum type, String nome, String city,
					   String state, LocalDate incomeDate, String genre, String runtime,
					   LocalDateTime screeningDateTime, String director,
					   String shortSynopsis, String fullSynopsis,
					   String directorBiography, String directorEmail) {
		super();
		this.id = id;
		this.type = type;
		this.name = nome;
		this.city = city;
		this.state = state;
		this.incomeDate = incomeDate;
		this.genre = genre;
		this.runtime = runtime;
		this.screeningDateTime = screeningDateTime;
		this.director = director;
		this.shortSynopsis = shortSynopsis;
		this.fullSynopsis = fullSynopsis;
		this.directorBiography = directorBiography;
		this.directorEmail = directorEmail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MovieTypeEnum getType() {
		return type;
	}

	public void setType(MovieTypeEnum type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public LocalDate getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(LocalDate incomeDate) {
		this.incomeDate = incomeDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public LocalDateTime getScreeningDateTime() {
		return screeningDateTime;
	}

	public void setScreeningDateTime(LocalDateTime screeningDateTime) {
		this.screeningDateTime = screeningDateTime;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getShortSynopsis() {
		return shortSynopsis;
	}

	public void setShortSynopsis(String shortSynopsis) {
		this.shortSynopsis = shortSynopsis;
	}

	public String getFullSynopsis() {
		return fullSynopsis;
	}

	public void setFullSynopsis(String fullSynopsis) {
		this.fullSynopsis = fullSynopsis;
	}

	public String getDirectorBiography() {
		return directorBiography;
	}

	public void setDirectorBiography(String directorBiography) {
		this.directorBiography = directorBiography;
	}

	public String getDirectorEmail() {
		return directorEmail;
	}

	public void setDirectorEmail(String directorEmail) {
		this.directorEmail = directorEmail;
	}

	public List<PhotoEntity> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PhotoEntity> photos) {
		this.photos = photos;
	}

	public List<VoteEntity> getVotes() {
		return votes;
	}

	public void setVotes(List<VoteEntity> votes) {
		this.votes = votes;
	}
}
