package com.cinefest.pojo;

import com.cinefest.movie.enumeration.MovieType;
import com.cinefest.pojo.PhotoVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MovieVO {

  private long id;
  private MovieType type;
  private String name;
  private String city;
  private String state;
  private LocalDate incomeDate;
  private String genre;
  private String runtime;
  private LocalDateTime screeningDateTime;
  private String director;
  private String shortSynopsis;
  private String fullSynopsis;
  private String directorBiography;
  private String directorEmail;
  private List<PhotoVO> photos;

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

  public void setName(String name) {
    this.name = name;
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

  public List<PhotoVO> getPhotos() {
    return photos;
  }

  public void setPhotos(List<PhotoVO> photos) {
    this.photos = photos;
  }
}