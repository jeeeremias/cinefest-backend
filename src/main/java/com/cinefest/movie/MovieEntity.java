package com.cinefest.movie;

import com.cinefest.photo.PhotoEntity;
import com.cinefest.rate.RateEntity;
import com.cinefest.movie.enumeration.MovieType;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class MovieEntity {

  @Id
  @GeneratedValue
  private long id;

  @Column
  private MovieType type;

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
  private Duration runtime;

  @Column
  private LocalDateTime screeningDateTime;

  @Column
  private String director;

  @Column(length = 5000)
  private String synopsis;

  @Column(length = 5000)
  private String directorBiography;

  @Column
  private String directorEmail;

  @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
  private List<PhotoEntity> photos;

  @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
  private List<RateEntity> rates;

  public MovieEntity() {
    super();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public MovieType getType() {
    return type;
  }

  public void setType(MovieType type) {
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

  public Duration getRuntime() {
    return runtime;
  }

  public void setRuntime(Duration runtime) {
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

  public String getSynopsis() {
    return synopsis;
  }

  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
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

  public List<RateEntity> getRates() {
    return rates;
  }

  public void setRates(List<RateEntity> rates) {
    this.rates = rates;
  }
}
