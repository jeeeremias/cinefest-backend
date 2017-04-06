package com.cinefest.rest.facade;

import com.cinefest.entity.MovieEntity;
import com.cinefest.pojo.params.QueryCriteria;
import com.cinefest.pojo.params.QueryParams;
import com.cinefest.service.MovieService;
import com.cinefest.util.enumeration.MovieAttrsEnum;
import com.cinefest.util.enumeration.MovieTypeEnum;
import com.cinefest.util.enumeration.QueryOperatorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.cinefest.util.helper.QueryCriteriaHelpers.createCriteriaFromValue;

@Service
public class MovieRestFacade extends BaseRestFacade<MovieEntity, MovieAttrsEnum> {

	@Autowired
    MovieService movieService;

    @Override
	public List<MovieEntity> getAll(Map<String, String> params) {
	    QueryParams queryParams = toQueryParams(params);
		return movieService.getAll(queryParams);
	}

    @Override
    protected QueryParams toQueryParams(Map<String, String> params) {
        QueryParams queryParams = new QueryParams();
        queryParams.setPagingAndSortingParams(pagingAndSortingParamsConverter.convertToQueryParam(params));

        params
                .entrySet()
                .forEach(e -> {
                    MovieAttrsEnum attrEnum = MovieAttrsEnum.fromQueryAttr(e.getKey());
                    if (attrEnum.searchable) {
                        queryParams.addCriteria(createCriteria(attrEnum, e.getValue()));
                    }
                });

        return queryParams;
    }

    @Override
    protected boolean isSortableParam(String param) {
        MovieAttrsEnum e = MovieAttrsEnum.fromQueryAttr(param);
        if (e != null) {
            return e.sortable;
        }
        return false;
    }

    @Override
    protected boolean isSearchableParam(String param) {
        MovieAttrsEnum e = MovieAttrsEnum.fromQueryAttr(param);
        if (e != null) {
            return e.searchable;
        }
        return false;
    }

    private Date toDate(String date) {
        return new Date();
    }

    private QueryCriteria createCriteria(MovieAttrsEnum attrEnum, String value) {
        QueryCriteria queryCriteria = new QueryCriteria();
        queryCriteria.setKey(attrEnum.entityAttr);

        return Arrays.stream(QueryOperatorEnum.values())
                .filter(e -> value.startsWith(e.op))
                .map(e -> {
                    queryCriteria.setValue(value.substring(1));
                    queryCriteria.setOp(e);
                    return queryCriteria;
                })
                .findFirst()
                .orElseGet(() -> {
                    queryCriteria.setValue(value);
                    queryCriteria.setOp(QueryOperatorEnum.EQUALS);
                    return queryCriteria;
                });
    }
}
