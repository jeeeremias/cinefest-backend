package com.cinefest.pojo.params;

import com.cinefest.util.enumeration.MovieAttr;
import com.cinefest.util.enumeration.QueryOperator;

public class QueryCriteria {

    MovieAttr key;
    QueryOperator op;
    String value;

    public MovieAttr getKey() {
        return key;
    }

    public void setKey(MovieAttr key) {
        this.key = key;
    }

    public QueryOperator getOp() {
        return op;
    }

    public void setOp(QueryOperator op) {
        this.op = op;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
