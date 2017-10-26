package com.cinefest.rate;

import com.cinefest.entity.UserEntity;
import com.cinefest.movie.MovieEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RateEntity {

  @Id
  @GeneratedValue
  private long id;

  @Column
  private double rate;

  @Column
  private LocalDateTime dateTime;

  @ManyToOne
  private UserEntity user;

  @ManyToOne
  private MovieEntity movie;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public double getRate() {
    return rate;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public MovieEntity getMovie() {
    return movie;
  }

  public void setMovie(MovieEntity movie) {
    this.movie = movie;
  }
}
