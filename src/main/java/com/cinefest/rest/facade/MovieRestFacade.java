package com.cinefest.rest.facade;

import com.cinefest.pojo.dto.MovieDTO;
import com.cinefest.pojo.params.QueryCriteria;
import com.cinefest.pojo.params.SearchCriteria;
import com.cinefest.rest.util.converter.PagingAndSortingParamsConverter;
import com.cinefest.service.MovieService;
import com.cinefest.specification.MovieSpecification;
import com.cinefest.util.converter.MovieConverter;
import com.cinefest.util.enumeration.MovieAttr;
import com.cinefest.util.enumeration.QueryOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class MovieRestFacade {

	@Autowired
    private MovieService movieService;

    @Autowired
    private PagingAndSortingParamsConverter pagingAndSortingParamsConverter;

	public List<MovieDTO> getAll(Map<String, String> params) {
        SearchCriteria searchCriteria = null;
        if (params != null) {
            searchCriteria = toQueryParams(params);
        }
		return MovieConverter.vosToDtos(movieService.getAll(searchCriteria));
	}

    public MovieDTO getOne(Long id) {
        return MovieConverter.voToDto(movieService.getOne(id));
    }

    private SearchCriteria toQueryParams(Map<String, String> params) {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setPagingAndSortingParams(pagingAndSortingParamsConverter.convertToQueryParam(params));

        params.entrySet()
                .forEach(e -> {
                    MovieAttr attrEnum = MovieAttr.fromQueryAttr(e.getKey());
                    if (attrEnum == null || e.getValue() == null) {
                        return;
                    }
                    if (!attrEnum.searchable) {
                        return;
                    }
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
