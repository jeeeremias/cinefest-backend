package com.cinefest.rate.impl;

import com.cinefest.movie.MovieSearchElement;
import com.cinefest.pojo.RateVO;
import com.cinefest.rate.RateService;
import com.cinefest.rest.params.SearchCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {

  @Override
  public List<RateVO> getAll(SearchCriteria<MovieSearchElement> searchCriteria) {
    return null;
  }

  @Override
  public RateVO getOne(long id) {
    return null;
  }

  @Override
  public RateVO create(RateVO movieVO) {
    return null;
  }

  @Override
  public RateVO update(long id, RateVO movieVO) {
    return null;
  }
}
