package com.cinefest.movie.enumeration;

import java.util.Arrays;

public enum MovieType {

  NACIONAL("Nacional"),
  REGIONAL("Regional"),
  AMBIENTAL("Ambiental"),
  PARALELA("Paralela"),
  MINUTO("Minuto");

  public final String desc;

  MovieType(String desc) {
    this.desc = desc;
  }

  public static MovieType fromDesc(String desc) {
    return Arrays.stream(MovieType.values())
      .filter(e -> e.desc.equals(desc))
      .findFirst()
      .orElse(null);
  }
}
