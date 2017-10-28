package com.cinefest.service.util.enumeration;

import java.util.Arrays;

public enum QueryOperator {

  GREATER(">"),
  LESS("<"),
  EQUALS(":"),
  LIKE("*");

  public final String op;

  QueryOperator(String op) {
    this.op = op;
  }

  public static QueryOperator fromOp(char op) {
    return Arrays.stream(QueryOperator.values())
      .filter(e -> e.op.equals(op))
      .findFirst()
      .orElse(null);
  }
}
