package com.cinefest.rate;

import com.cinefest.pojo.RateVO;
import com.cinefest.rest.params.SearchCriteria;

import java.util.List;

public interface RateService {

  List<RateVO> getAll(SearchCriteria<RateSearchElement> searchCriteria);

  RateVO getOne(long id);

  RateVO create(RateVO movieVO);

  RateVO update(long id, RateVO movieVO);
}
