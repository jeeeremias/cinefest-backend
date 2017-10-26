package com.cinefest.movie;

public interface PathEndpoints {
  String RATES = "/rates";
  String MOVIES = "/movies";
  String ID = "/{id}";
  String RATE_ID = RATES + ID;
  String MOVIE_ID = MOVIES + ID;
}
