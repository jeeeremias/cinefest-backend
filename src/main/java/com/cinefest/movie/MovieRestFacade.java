package com.cinefest.movie;

import com.cinefest.movie.pojo.MovieDTO;
import com.cinefest.movie.pojo.MovieSearchElement;
import com.cinefest.rest.params.SearchCriteria;
import com.cinefest.rest.util.converter.PagingAndSortingParamsConverter;
import com.cinefest.movie.enumeration.MovieAttr;
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
      searchCriteria = toMovieQuery(params);
    }
    return MovieConverter.vosToDtos(movieService.getAll(searchCriteria));
  }

  public MovieDTO getOne(Long id) {
    return MovieConverter.voToDto(movieService.getOne(id));
  }

  private SearchCriteria toMovieQuery(Map<String, String> params) {
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
        MovieSearchElement searchElem = createCriteria(e.getValue());
        searchElem.setKey(attrEnum);
        searchCriteria.addSearch(searchElem);
      });

    return searchCriteria;
  }

  private MovieSearchElement createCriteria(String value) {
    MovieSearchElement movieSearchCriteria = new MovieSearchElement();

    return Arrays.stream(QueryOperator.values())
      .filter(e -> value.startsWith(e.op))
      .map(e -> {
        movieSearchCriteria.setValue(value.substring(1));
        movieSearchCriteria.setOp(e);
        return movieSearchCriteria;
      })
      .findFirst()
      .orElseGet(() -> {
        movieSearchCriteria.setValue(value);
        movieSearchCriteria.setOp(QueryOperator.EQUALS);
        return movieSearchCriteria;
      });
  }
}
