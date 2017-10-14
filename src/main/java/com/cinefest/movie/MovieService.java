package com.cinefest.movie;

import com.cinefest.pojo.MovieVO;
import com.cinefest.rest.params.SearchCriteria;

import java.util.List;

public interface MovieService {

  List<MovieVO> getAll(SearchCriteria<MovieSearchElement> searchCriteria);

  MovieVO getOne(long id);

  MovieVO create(MovieVO movieVO);

  MovieVO update(long id, MovieVO movieVO);
}
