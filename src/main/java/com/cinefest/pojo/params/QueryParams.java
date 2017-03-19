package com.cinefest.pojo.params;

import java.util.HashMap;
import java.util.Map;

public class QueryParams {
    PagingAndSortingParams pagingAndSortingParams;
    Map<String, String> genericParams;

    public PagingAndSortingParams getPagingAndSortingParams() {
        return pagingAndSortingParams;
    }

    public void setPagingAndSortingParams(PagingAndSortingParams pagingAndSortingParams) {
        this.pagingAndSortingParams = pagingAndSortingParams;
    }

    public Map<String, String> getGenericParams() {
        return genericParams;
    }

    public void setGenericParams(Map<String, String> genericParams) {
        this.genericParams = genericParams;
    }

    public void addGenericParam(String key, String value) {
        if (genericParams == null) {
            genericParams = new HashMap<>();
        }
        genericParams.put(key, value);
    }
}
