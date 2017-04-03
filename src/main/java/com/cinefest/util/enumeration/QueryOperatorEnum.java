package com.cinefest.util.enumeration;

import java.util.Arrays;

public enum QueryOperatorEnum {

    GREATER(">"),
    LESS("<"),
    EQUALS(":"),
    LIKE("*");

    public final String op;

    QueryOperatorEnum(String op) {
        this.op = op;
    }

    public static QueryOperatorEnum fromOp(char op) {
        return Arrays.stream(QueryOperatorEnum.values())
                .filter(e -> e.op.equals(op))
                .findFirst()
                .orElse(null);
    }
}
