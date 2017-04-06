package com.cinefest.pojo.params;

import com.cinefest.util.enumeration.QueryOperatorEnum;

public class QueryCriteria {

    String key;
    QueryOperatorEnum op;
    String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public QueryOperatorEnum getOp() {
        return op;
    }

    public void setOp(QueryOperatorEnum op) {
        this.op = op;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
