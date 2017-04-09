package com.cinefest.rest.facade;

import com.cinefest.pojo.params.SearchCriteria;
import com.cinefest.rest.util.converter.PagingAndSortingParamsConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public abstract class BaseRestFacade<T> {

    @Autowired
    protected PagingAndSortingParamsConverter pagingAndSortingParamsConverter;

    protected abstract SearchCriteria toQueryParams(Map<String, String> params);

    public abstract List<T> getAll(Map<String, String> params);

    public abstract T getOne(Long id);
}
