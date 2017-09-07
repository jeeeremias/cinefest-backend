package com.cinefest.movie.impl;

import com.cinefest.movie.MovieConverter;
import com.cinefest.movie.MovieRestAdapter;
import com.cinefest.movie.MovieService;
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
public class MovieRestAdapterImpl implements MovieRestAdapter {

  PagingAndSortingParamsConverter pagingAndSortingParamsConverter;
  MovieService service;

  @Autowired
  public MovieRestAdapterImpl(PagingAndSortingParamsConverter pagingAndSortingParamsConverter,
                              MovieService service) {
    this.pagingAndSortingParamsConverter = pagingAndSortingParamsConverter;
    this.service = service;
  }

  @Override
  public List<MovieDTO> getDTOs(Map<String, String> params) {
    SearchCriteria searchCriteria = null;
    if (params != null) {
      searchCriteria = toMovieQuery(params);
    }
    return MovieConverter.vosToDtos(service.getAll(searchCriteria));
  }

  @Override
  public MovieDTO getDTOById(long id) {
    return MovieConverter.voToDto(service.getOne(id));
  }

  @Override
  public MovieDTO newFromDTO(MovieDTO movieDTO) {
    return MovieConverter.voToDto(service.create(MovieConverter.dtoToVO(movieDTO)));
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
