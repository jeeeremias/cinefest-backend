package com.cinefest.rest.facade;

import com.cinefest.movie.MovieEntity;
import com.cinefest.review.ReviewEntity;
import com.cinefest.rest.params.SearchCriteria;
import com.cinefest.movie.MovieRepository;
import com.cinefest.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VoteRestFacade {

  @Autowired
  VoteRepository voteRespository;

  @Autowired
  MovieRepository movieRespository;

  public ReviewEntity save(ReviewEntity reviewEntity) {
    MovieEntity movieEntity = movieRespository.findOne(reviewEntity.getMovieId());
    reviewEntity.setMovie(movieEntity);
    return voteRespository.save(reviewEntity);
  }

  public Iterable<ReviewEntity> getAll(SearchCriteria params) {
    return voteRespository.findAll(new Sort(Sort.Direction.ASC, "day"));
  }

  public Long countByDia(String day) {
    return voteRespository.countByDateTime(day);
  }
}
