package com.cinefest.movie;

public interface MovieEndpoints {
  String MOVIES = "/movies";
  String ID = "/{id}";
  String MOVIE_BY_ID = MOVIES + ID;
}
