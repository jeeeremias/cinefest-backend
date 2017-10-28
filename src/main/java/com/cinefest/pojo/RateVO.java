package com.cinefest.pojo;

import java.time.LocalDateTime;

public class RateVO {

  private long id;
  private double rate;
  private LocalDateTime dateTime;
  private UserVO user;

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

  public UserVO getUser() {
    return user;
  }

  public void setUser(UserVO user) {
    this.user = user;
  }
}
