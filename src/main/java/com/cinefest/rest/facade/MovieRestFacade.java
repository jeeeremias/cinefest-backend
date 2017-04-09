package com.cinefest.rest.facade;

import com.cinefest.entity.MovieEntity;
import com.cinefest.pojo.params.QueryCriteria;
import com.cinefest.pojo.params.SearchCriteria;
import com.cinefest.service.MovieService;
import com.cinefest.specification.MovieSpecification;
import com.cinefest.util.enumeration.MovieAttr;
import com.cinefest.util.enumeration.QueryOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MovieRestFacade extends BaseRestFacade<MovieEntity> {

	@Autowired
    MovieService movieService;

    @Override
	public List<MovieEntity> getAll(Map<String, String> params) {
	    SearchCriteria searchCriteria = toQueryParams(params);
		return movieService.getAll(searchCriteria);
	}

    @Override
    public MovieEntity getOne(Long id) {
        return movieService.getOne(id);
    }

    @Override
    protected SearchCriteria toQueryParams(Map<String, String> params) {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setPagingAndSortingParams(pagingAndSortingParamsConverter.convertToQueryParam(params));

        params
                .entrySet()
                .forEach(e -> {
                    MovieAttr attrEnum = MovieAttr.fromQueryAttr(e.getKey());
                    if (attrEnum == null)
                        return;
                    if (!attrEnum.searchable)
                        return;
                    QueryCriteria criteria = createCriteria(e.getValue());
                    criteria.setKey(attrEnum);
                    searchCriteria.addSpecification(new MovieSpecification(criteria));
                });

        return searchCriteria;
    }

    private QueryCriteria createCriteria(String value) {
        QueryCriteria queryCriteria = new QueryCriteria();

        return Arrays.stream(QueryOperator.values())
                .filter(e -> value.startsWith(e.op))
                .map(e -> {
                    queryCriteria.setValue(value.substring(1));
                    queryCriteria.setOp(e);
                    return queryCriteria;
                })
                .findFirst()
                .orElseGet(() -> {
                    queryCriteria.setValue(value);
                    queryCriteria.setOp(QueryOperator.EQUALS);
                    return queryCriteria;
                });
    }
}
