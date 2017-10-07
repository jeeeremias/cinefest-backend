package com.cinefest.movie;

import com.cinefest.movie.pojo.MovieDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

import static com.cinefest.movie.MovieEndpoints.MOVIES;
import static com.cinefest.movie.MovieEndpoints.MOVIE_BY_ID;

@RestController
class MovieController {

  MovieRestAdapter movieRestAdapter;

  public MovieController(MovieRestAdapter movieRestAdapter) {
    this.movieRestAdapter = movieRestAdapter;
  }

  @RequestMapping(method = RequestMethod.GET, value = MOVIES, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Iterable<MovieDTO> getMovies(@RequestParam(required = false) Map<String, String> params) {
    return movieRestAdapter.getDTOs(params);
  }

  @RequestMapping(method = RequestMethod.GET, value = MOVIE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public MovieDTO getMovie(@PathParam("id") long id) {
    return movieRestAdapter.getDTOById(id);
  }

  @RequestMapping(method = RequestMethod.POST, value = MOVIES, produces = MediaType.APPLICATION_JSON_VALUE)
  public MovieDTO newMovie(@RequestBody MovieDTO movie) {
    return movieRestAdapter.newFromDTO(movie);
  }

  @RequestMapping(method = RequestMethod.PUT, value = MOVIE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public MovieDTO uptadeMovie(@PathParam("id") long id, @RequestBody MovieDTO movie) {
    return movieRestAdapter.newFromDTO(movie);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = MOVIE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public MovieEntity deleteMovie(@PathParam("id") long id, @RequestBody MovieDTO movie) throws IllegalAccessException {
    throw new IllegalAccessException();
  }

}
