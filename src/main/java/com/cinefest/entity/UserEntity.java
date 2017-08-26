package com.cinefest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class UserEntity implements Serializable {

  @Id
  @NotNull
  private String email;

  @Column
  private String name;

  @Column
  @NotNull
  private String password;

  public UserEntity() {
    super();
  }

  public UserEntity(String email, String name, String password) {
    super();
    this.email = email;
    this.name = name;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
