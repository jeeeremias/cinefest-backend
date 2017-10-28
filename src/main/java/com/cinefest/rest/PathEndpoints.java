package com.cinefest.rest;

public interface PathEndpoints {
  String RATES = "/rates";
  String MOVIES = "/movies";
  String SETUP = "/setup";
  String ID = "/{id}";
  String RATE_ID = RATES + ID;
  String MOVIE_ID = MOVIES + ID;
}
