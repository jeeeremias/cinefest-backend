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
  private MovieEntity movie;

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
