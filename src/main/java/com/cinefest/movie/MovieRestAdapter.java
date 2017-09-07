package com.cinefest.movie;

import com.cinefest.movie.pojo.MovieDTO;

import java.util.List;
import java.util.Map;

public interface MovieRestAdapter {

  List<MovieDTO> getDTOs(Map<String, String> params);

  MovieDTO getDTOById(long id);

  MovieDTO newFromDTO(MovieDTO movieVO);
}
