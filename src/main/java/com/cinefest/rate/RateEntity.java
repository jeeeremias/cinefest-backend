package com.cinefest.rate;

import com.cinefest.movie.MovieEntity;
import com.cinefest.user.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

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

  @ManyToOne(fetch = LAZY)
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
