package com.cinefest.review;

import com.cinefest.movie.MovieSearchElement;
import com.cinefest.pojo.VoteVO;
import com.cinefest.rest.params.SearchCriteria;

import java.util.List;

public interface ReviewService {

  List<VoteVO> getAll(SearchCriteria<MovieSearchElement> searchCriteria);

  VoteVO getOne(long id);

  VoteVO create(VoteVO movieVO);

  VoteVO update(long id, VoteVO movieVO);
}
