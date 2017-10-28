package com.cinefest.rate.impl;

import com.cinefest.pojo.RateVO;
import com.cinefest.rate.RateEntity;
import com.cinefest.rate.RateRepository;
import com.cinefest.rate.RateSearchElement;
import com.cinefest.rate.RateService;
import com.cinefest.rate.specification.RateSpecificationBuilder;
import com.cinefest.rest.params.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cinefest.rate.RateConverter.toVos;
import static com.cinefest.service.util.converter.PageRequestConverter.toPageRequest;

@Service
public class RateServiceImpl implements RateService {

  private RateRepository rateRepository;
  private RateSpecificationBuilder specificationBuilder;

  @Autowired
  public RateServiceImpl(RateRepository rateRepository, RateSpecificationBuilder specificationBuilder) {
    this.rateRepository = rateRepository;
    this.specificationBuilder = specificationBuilder;
  }

  @Override
  public List<RateVO> getAll(SearchCriteria<RateSearchElement> searchCriteria) {
    Specifications<RateEntity> specifications = null;
    PageRequest pageRequest = null;
    if (searchCriteria.getPageAndSortParams() != null) {
      pageRequest = toPageRequest(searchCriteria.getPageAndSortParams());
    }
    if (searchCriteria.getSearches() != null) {
      specifications = specificationBuilder.buildSpecifications(searchCriteria.getSearches());
    }

    if (pageRequest != null && specifications != null) {
      return toVos(rateRepository.findAll(specifications, pageRequest).getContent());
    } else if (pageRequest != null) {
      return toVos(rateRepository.findAll(pageRequest).getContent());
    } else {
      return toVos(rateRepository.findAll(specifications));
    }
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
