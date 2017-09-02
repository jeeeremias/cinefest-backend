package com.cinefest.entity;

import com.cinefest.movie.MovieEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class VoteEntity implements Serializable {

  @Id
  @GeneratedValue
  private long id;

  @Column
  private LocalDateTime dateTime;

  @Transient
  private long movieId;

  @ManyToOne
  @JoinColumn(name = "movieId")
  @JsonIgnore
  private MovieEntity movie;

  public VoteEntity() {
    super();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public MovieEntity getMovie() {
    return movie;
  }

  public void setMovie(MovieEntity movie) {
    this.movie = movie;
  }

  public long getMovieId() {
    return movieId;
  }

  public void setMovieId(long movieId) {
    this.movieId = movieId;
  }
}
