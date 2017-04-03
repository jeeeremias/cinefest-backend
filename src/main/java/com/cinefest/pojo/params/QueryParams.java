package com.cinefest.pojo.params;

import com.cinefest.util.enumeration.QueryOperatorEnum;

import java.util.ArrayList;
import java.util.List;

public class QueryParams {

    PagingAndSortingParams pagingAndSortingParams;
    List<QueryCriteria> criterias;

    public PagingAndSortingParams getPagingAndSortingParams() {
        return pagingAndSortingParams;
    }

    public void setPagingAndSortingParams(PagingAndSortingParams pagingAndSortingParams) {
        this.pagingAndSortingParams = pagingAndSortingParams;
    }

    public List<QueryCriteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<QueryCriteria> criterias) {
        this.criterias = criterias;
    }

    public void addCriteria(String key, QueryOperatorEnum op, Object value) {
        if (criterias == null) {
            criterias = new ArrayList<>();
        }
        criterias.add(new QueryCriteria(key, op, value));
    }
}
