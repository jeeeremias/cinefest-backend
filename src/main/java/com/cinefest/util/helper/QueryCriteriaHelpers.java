package com.cinefest.util.helper;

import com.cinefest.pojo.params.MovieSearchElement;
import com.cinefest.util.enumeration.QueryOperator;

import java.util.Arrays;

public class QueryCriteriaHelpers {

  public static MovieSearchElement createCriteriaFromValue(String value) {
    MovieSearchElement criteria = new MovieSearchElement();
    Arrays.stream(QueryOperator.values())
      .filter(e -> value.startsWith(e.op))
      .map(e -> {
        criteria.setOp(e);
        criteria.setValue(value.substring(1));
        return criteria;
      })
      .findFirst()
      .orElseGet(() -> {
        criteria.setOp(QueryOperator.EQUALS);
        criteria.setValue(value);
        return criteria;
      });
    return criteria;
  }
}
