package com.cinefest.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class UserEntity {

  @Id
  @GeneratedValue
  private String id;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private String email;

  @Column
  private String cellPhone;

  @Column
  private LocalDate bornDate;
}
