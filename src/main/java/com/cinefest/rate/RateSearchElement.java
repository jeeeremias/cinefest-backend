package com.cinefest.rate;

import com.cinefest.search.SearchElement;
import com.cinefest.service.util.enumeration.QueryOperator;

public class RateSearchElement implements SearchElement<RateAttr> {

  RateAttr key;
  QueryOperator op;
  String value;

  @Override
  public RateAttr getKey() {
    return key;
  }

  public void setKey(RateAttr key) {
    this.key = key;
  }

  @Override
  public QueryOperator getOp() {
    return op;
  }

  public void setOp(QueryOperator op) {
    this.op = op;
  }

  @Override
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
