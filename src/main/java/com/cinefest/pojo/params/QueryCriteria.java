package com.cinefest.pojo.params;

import com.cinefest.util.enumeration.QueryOperatorEnum;

public class QueryCriteria {

    String key;
    QueryOperatorEnum op;
    Object value;

    public QueryCriteria(String key, QueryOperatorEnum op, Object value) {
        this.key = key;
        this.op = op;
        this.value = value;
    }

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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
