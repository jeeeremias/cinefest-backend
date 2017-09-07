package com.cinefest.movie;

import com.cinefest.movie.impl.MovieRestAdapterImpl;
import com.cinefest.movie.pojo.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.websocket.server.PathParam;
import java.util.Map;

import static com.cinefest.movie.MovieEndpoints.MOVIES;
import static com.cinefest.movie.MovieEndpoints.MOVIE_BY_ID;

@RestController
class MovieController {

  @Autowired
  MovieRestAdapterImpl movieRestAdapterImpl;

  @RequestMapping(method = RequestMethod.GET, value = MOVIES, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Iterable<MovieDTO> getMovies(@RequestParam(required = false) Map<String, String> params) {
    return movieRestAdapterImpl.getDTOs(params);
  }

  @RequestMapping(method = RequestMethod.GET, value = MOVIE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public MovieDTO getMovie(@PathParam("id") long id) {
    return movieRestAdapterImpl.getDTOById(id);
  }

  @RequestMapping(method = RequestMethod.POST, value = MOVIES, produces = MediaType.APPLICATION_JSON_VALUE)
  public MovieDTO newMovie(@RequestBody MovieDTO movie) {
    return movieRestAdapterImpl.newFromDTO(movie);
  }

  @RequestMapping(method = RequestMethod.PUT, value = MOVIE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public MovieDTO uptadeMovie(@PathParam("id") long id, @RequestBody MovieDTO movie) {
    return movieRestAdapterImpl.newFromDTO(movie);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = MOVIE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public MovieEntity deleteMovie(@PathParam("id") long id, @RequestBody MovieDTO movie) {
    throw new NotImplementedException();
  }

}
