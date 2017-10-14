package com.cinefest.rate;

import com.cinefest.util.enumeration.ParamType;

public enum RateAttr {
  
  RATE("rate", "rate", true, true, ParamType.DOUBLE),
  USER("user", "user", true, true, ParamType.CUSTOM),
  MOVIE("movie", "movie", true, true, ParamType.CUSTOM),
  REVIEW("review", "review", false, true, ParamType.STRING);

  public final String queryAttr;
  public final String entityAttr;
  public final boolean sortable;
  public final boolean searchable;
  public final ParamType type;

  RateAttr(String queryAttr, String entityAttr, boolean sortable, boolean searchable, ParamType type) {
    this.queryAttr = queryAttr;
    this.entityAttr = entityAttr;
    this.sortable = sortable;
    this.searchable = searchable;
    this.type = type;
  }
}
