package com.cinefest.entity;

import com.cinefest.movie.MovieEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PhotoEntity implements Serializable {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String source;

  @Column
  private boolean main;

  @ManyToOne
  @JoinColumn(name = "idMovie")
  @JsonIgnore
  private MovieEntity movie;

  public PhotoEntity() {
    super();
  }

  public PhotoEntity(String source, boolean main, MovieEntity movie) {
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

  public MovieEntity getMovie() {
    return movie;
  }

  public void setMovie(MovieEntity movie) {
    this.movie = movie;
  }

  public boolean isMain() {
    return main;
  }

  public void setMain(boolean main) {
    this.main = main;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
