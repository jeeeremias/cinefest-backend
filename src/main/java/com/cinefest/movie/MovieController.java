package com.cinefest.movie;

import com.cinefest.movie.enumeration.MovieAttr;
import com.cinefest.pojo.MovieVO;
import com.cinefest.rest.params.SearchCriteria;
import com.cinefest.rest.util.converter.PagingAndSortingParamsConverter;
import com.cinefest.util.enumeration.QueryOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cinefest.rest.PathEndpoints.MOVIES;
import static com.cinefest.rest.PathEndpoints.MOVIE_ID;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
class MovieController {

  private PagingAndSortingParamsConverter pagingAndSortingParamsConverter;
  private MovieService movieService;

  @Autowired
  public MovieController(PagingAndSortingParamsConverter pagingAndSortingParamsConverter, MovieService movieService) {
    this.pagingAndSortingParamsConverter = pagingAndSortingParamsConverter;
    this.movieService = movieService;
  }

  @RequestMapping(method = GET, value = MOVIES, produces = APPLICATION_JSON_VALUE)
  public List<MovieVO> getMovies(@RequestParam(required = false) Map<String, String> params) {
    SearchCriteria searchCriteria = toMovieQuery(params);
    return movieService.getAll(searchCriteria);
  }

  @RequestMapping(method = GET, value = MOVIE_ID, produces = APPLICATION_JSON_VALUE)
  public MovieVO getMovie(@PathParam("id") long id) {
    return movieService.getOne(id);
  }

  @ResponseStatus(CREATED)
  @RequestMapping(method = POST, value = MOVIES, produces = APPLICATION_JSON_VALUE)
  public MovieVO newMovie(@RequestBody MovieVO movie) {
    return movieService.create(movie);
  }

  @RequestMapping(method = PUT, value = MOVIE_ID, produces = APPLICATION_JSON_VALUE)
  public MovieVO uptadeMovie(@PathParam("id") long id, @RequestBody MovieVO movie) {
    return movieService.update(id, movie);
  }

  @ResponseStatus(NO_CONTENT)
  @RequestMapping(method = DELETE, value = MOVIE_ID, produces = APPLICATION_JSON_VALUE)
  public void deleteMovie(@PathParam("id") long id) throws IllegalAccessException {
    movieService.delete(id);
  }

  private SearchCriteria toMovieQuery(Map<String, String> params) {
    SearchCriteria searchCriteria = new SearchCriteria();
    if (params == null) {
      params = new HashMap<>();
    }
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
