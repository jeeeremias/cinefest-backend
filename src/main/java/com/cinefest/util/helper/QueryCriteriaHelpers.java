package com.cinefest.util.helper;

import com.cinefest.pojo.params.QueryCriteria;
import com.cinefest.util.enumeration.QueryOperatorEnum;

import java.util.Arrays;

public class QueryCriteriaHelpers {

    public static QueryCriteria createCriteriaFromValue(String value) {
        QueryCriteria criteria = new QueryCriteria();
        Arrays.stream(QueryOperatorEnum.values())
                .filter(e -> value.startsWith(e.op))
                .map(e -> {
                    criteria.setOp(e);
                    criteria.setValue(value.substring(1));
                    return criteria;
                })
                .findFirst()
                .orElseGet(() -> {
                    criteria.setOp(QueryOperatorEnum.EQUALS);
                    criteria.setValue(value);
                    return criteria;
                });
        return criteria;
    }
}
