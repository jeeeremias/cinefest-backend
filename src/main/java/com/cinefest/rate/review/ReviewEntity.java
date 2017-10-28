package com.cinefest.rate.review;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReviewEntity {

  @Id
  @GeneratedValue
  private long id;

  @Column
  private long rateId;

  @Column
  private String review;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getRateId() {
    return rateId;
  }

  public void setRateId(long rateId) {
    this.rateId = rateId;
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }
}
