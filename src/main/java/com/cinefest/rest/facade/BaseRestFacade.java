package com.cinefest.rest.facade;

import com.cinefest.pojo.params.QueryParams;
import com.cinefest.rest.util.converter.PagingAndSortingParamsConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public abstract class BaseRestFacade<T, E> {

    @Autowired
    protected PagingAndSortingParamsConverter pagingAndSortingParamsConverter;

    protected abstract QueryParams toQueryParams(Map<String, String> params);

    protected abstract boolean isSortableParam(String param);

    protected abstract boolean isSearchableParam(String param);

    protected abstract Object getObjectValueParam(E enumParam, String value);

    public abstract List<T> getAll(Map<String, String> params);
}
