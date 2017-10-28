package com.cinefest.search;

import com.cinefest.service.util.enumeration.QueryOperator;

public interface SearchElement<T> {

  T getKey();

  QueryOperator getOp();

  String getValue();
}
