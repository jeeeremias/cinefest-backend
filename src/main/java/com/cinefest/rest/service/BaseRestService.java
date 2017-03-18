package com.cinefest.rest.service;

import com.cinefest.pojo.params.QueryParams;
import com.cinefest.rest.util.converter.GenericQueryParamsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jere on 18/03/17.
 */
public abstract class BaseRestService<T> {

    @Autowired
    GenericQueryParamsConverter queryParamsConverter;

    protected PageRequest createPageRequest(int offset, int size, List<String> sortParams) {
        Sort.Direction direction;
        List<Sort.Order> orders = new ArrayList<>();
        for (String prop : sortParams) {
            if (prop.startsWith("-")) {
                direction = Sort.Direction.DESC;
                prop = prop.substring(1);
            } else {
                direction = Sort.Direction.ASC;
            }
            orders.add(new Sort.Order(direction, prop));
        }
        Sort sort = new Sort(orders);
        PageRequest pageRequest = new PageRequest(offset, size, sort);
        return pageRequest;
    }

    protected QueryParams toQueryParams(Map<String, String> params) {
        return queryParamsConverter.convertToQueryParam(params);
    }

    public abstract List<T> getAll (Map<String, String> params);
}
