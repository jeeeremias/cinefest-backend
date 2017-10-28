package com.cinefest.pojo;

import javax.persistence.Column;
import java.time.LocalDate;

public class UserVO {

  private String id;
  private String firstName;
  private String lastName;
  private String email;
  private String cellPhone;
  private LocalDate bornDate;
  private boolean privateEmail;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCellPhone() {
    return cellPhone;
  }

  public void setCellPhone(String cellPhone) {
    this.cellPhone = cellPhone;
  }

  public LocalDate getBornDate() {
    return bornDate;
  }

  public void setBornDate(LocalDate bornDate) {
    this.bornDate = bornDate;
  }

  public boolean isPrivateEmail() {
    return privateEmail;
  }

  public void setPrivateEmail(boolean privateEmail) {
    this.privateEmail = privateEmail;
  }
}
