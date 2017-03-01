package com.cinefest.util.converter.rest;

import com.cinefest.pojo.params.QueryParams;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GenericQueryParamsConverter {

    private static final String[] GENERIC_PARAMS = {
            "page", "size", "sort"
    };

    public QueryParams convertToQueryParam(Map<String, String> params) {
        QueryParams queryParams = new QueryParams();

        queryParams.setPage(getNaturalAttr(params, "page", 0));
        queryParams.setSize(getNaturalAttr(params, "size", 100));
        queryParams.setSort(getListAttr(params, "sort"));

        for (String value : GENERIC_PARAMS) {
            params.remove(value);
        }

        queryParams.setGenericParams(params);

        return queryParams;
    }

    private List<String> getListAttr(Map<String, String> params, String attr) {
        String value = params.get(attr);
        if (value == null) {
            return null;
        }
        return Arrays.asList(value.split(","));
    }

    private int getNaturalAttr(Map<String, String> params, String attr, int defaultValue) {
        int num = getIntegerAttr(params, attr, defaultValue);

        if (num < 0) {
            // TODO: Properly exception here
        }

        return num;
    }

    private int getIntegerAttr(Map<String, String> params, String attr, int defaultValue) {
        String value = params.get(attr);
        if (value == null) {
            return defaultValue;
        }

        Integer num = Integer.valueOf(value);
        if (num == null) {
            // TODO: Properly exception here
        }

        return num;
    }
}
