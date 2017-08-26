package com.cinefest.rest.facade;

import com.cinefest.entity.MovieEntity;
import com.cinefest.entity.VoteEntity;
import com.cinefest.pojo.params.SearchCriteria;
import com.cinefest.repository.MovieRepository;
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

  public VoteEntity save(VoteEntity voteEntity) {
    MovieEntity movieEntity = movieRespository.findOne(voteEntity.getMovieId());
    voteEntity.setMovie(movieEntity);
    return voteRespository.save(voteEntity);
  }

  public Iterable<VoteEntity> getAll(SearchCriteria params) {
    return voteRespository.findAll(new Sort(Sort.Direction.ASC, "day"));
  }

  public Long countByDia(String day) {
    return voteRespository.countByDateTime(day);
  }
}
