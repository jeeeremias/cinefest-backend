package com.cinefest.rest.service;

import com.cinefest.pojo.params.QueryParams;
import com.cinefest.rest.util.converter.GenericQueryParamsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

public abstract class BaseRestService<T> {

    @Autowired
    protected GenericQueryParamsConverter queryParamsConverter;

    protected abstract QueryParams toQueryParams(Map<String, String> params);

    protected abstract boolean isSortableParam(String param);

    protected abstract boolean isSearchableParam(String param);

    public abstract List<T> getAll(Map<String, String> params);
}
