package com.cinefest.movie;

import com.cinefest.movie.enumeration.MovieAttr;
import com.cinefest.movie.pojo.MovieSearchElement;
import com.cinefest.movie.pojo.MovieVO;
import com.cinefest.rest.params.SearchCriteria;
import com.cinefest.rest.util.converter.PagingAndSortingParamsConverter;
import com.cinefest.util.enumeration.QueryOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.cinefest.movie.MovieEndpoints.MOVIES;
import static com.cinefest.movie.MovieEndpoints.MOVIE_BY_ID;

@RestController
class MovieController {

  PagingAndSortingParamsConverter pagingAndSortingParamsConverter;
  MovieService movieService;

  @Autowired
  public MovieController(PagingAndSortingParamsConverter pagingAndSortingParamsConverter, MovieService movieService) {
    this.pagingAndSortingParamsConverter = pagingAndSortingParamsConverter;
    this.movieService = movieService;
  }

  @RequestMapping(method = RequestMethod.GET, value = MOVIES, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<MovieVO> getMovies(@RequestParam(required = false) Map<String, String> params) {
    SearchCriteria searchCriteria = null;
    if (params != null) {
      searchCriteria = toMovieQuery(params);
    }
    return movieService.getAll(searchCriteria);
  }

  @RequestMapping(method = RequestMethod.GET, value = MOVIE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public MovieVO getMovie(@PathParam("id") long id) {
    return movieService.getOne(id);
  }

  @RequestMapping(method = RequestMethod.POST, value = MOVIES, produces = MediaType.APPLICATION_JSON_VALUE)
  public MovieVO newMovie(@RequestBody MovieVO movie) {
    return movieService.create(movie);
  }

  @RequestMapping(method = RequestMethod.PUT, value = MOVIE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public MovieVO uptadeMovie(@PathParam("id") long id, @RequestBody MovieVO movie) {
    return movieService.create(movie);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = MOVIE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public MovieVO deleteMovie(@PathParam("id") long id, @RequestBody MovieVO movie) throws IllegalAccessException {
    throw new IllegalAccessException();
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
