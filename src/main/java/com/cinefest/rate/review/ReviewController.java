package com.cinefest.rate.review;

import com.cinefest.movie.MovieService;
import com.cinefest.rest.params.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController("/votes")
public class ReviewController {

  MovieService movieService;

  @Autowired
  public ReviewController(MovieService movieService) {
    this.movieService = movieService;
  }

  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
  @ResponseBody
  public Iterable<ReviewEntity> getVotes(@RequestParam SearchCriteria params) throws IllegalAccessException {
    // TODO: Implement
    throw new IllegalAccessException();
  }

  @RequestMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  @ResponseBody
  public ReviewEntity updateVote(@RequestBody ReviewEntity reviewEntity) throws IllegalAccessException {
    // TODO: Implement
    throw new IllegalAccessException();
  }

  @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  @ResponseBody
  public ReviewEntity vote(@RequestBody ReviewEntity reviewEntity) throws IllegalAccessException {
    // TODO: Implement
    throw new IllegalAccessException();
  }

}
