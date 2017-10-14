package com.cinefest.rate;

public interface RateEndpoints {
  String RATES = "/rates";
  String ID = "/{id}";
  String RATES_BY_ID = RATES + ID;
}
