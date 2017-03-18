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

    protected PageRequest createPageRequest(int offset, int size, List<String> sortParams) {
        Sort.Direction direction;
        List<Sort.Order> orders = new ArrayList<>();
        String prop;
        for (String param : sortParams) {
            if (param.startsWith("-")) {
                direction = Sort.Direction.DESC;
                prop = param.substring(1);
            } else {
                direction = Sort.Direction.ASC;
                prop = param;
            }
            if (isSortableParam(prop)) {
                orders.add(new Sort.Order(direction, prop));
            }
        }
        Sort sort = new Sort(orders);
        PageRequest pageRequest = new PageRequest(offset, size, sort);
        return pageRequest;
    }

    protected abstract QueryParams toQueryParams(Map<String, String> params);

    protected abstract boolean isSortableParam(String param);

    protected abstract boolean isSearchableParam(String param);

    public abstract List<T> getAll (Map<String, String> params);
}
