package com.cinefest.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Movie implements Serializable {
	
	@Id
	private Integer id;
	
	@Column
	private String type;
	
	@Column
	private String name;
	
	@Column
	private String city;
	
	@Column
	private String state;
	
	@Column
	private Integer releaseYear;
	
	@Column
	private String genre;
	
	@Column
	private String runtime;
	
	@Column
	private String screeningDate;
	
	@Column
	private String screeningTime;
	
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
	private List<Photo> photos;

	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Vote> votes;

	public Movie() {
		super();
	}

	public Movie(Integer id, String type, String nome, String city,
				 String state, Integer releaseYear, String genre, String runtime,
				 String screeningDate, String screeningTime, String director,
				 String shortSynopsis, String fullSynopsis,
				 String directorBiography, String directorEmail) {
		super();
		this.id = id;
		this.type = type;
		this.name = nome;
		this.city = city;
		this.state = state;
		this.releaseYear = releaseYear;
		this.genre = genre;
		this.runtime = runtime;
		this.screeningDate = screeningDate;
		this.screeningTime = screeningTime;
		this.director = director;
		this.shortSynopsis = shortSynopsis;
		this.fullSynopsis = fullSynopsis;
		this.directorBiography = directorBiography;
		this.directorEmail = directorEmail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
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

	public String getScreeningDate() {
		return screeningDate;
	}

	public void setScreeningDate(String screeningDate) {
		this.screeningDate = screeningDate;
	}

	public String getScreeningTime() {
		return screeningTime;
	}

	public void setScreeningTime(String screeningTime) {
		this.screeningTime = screeningTime;
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

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}
}
