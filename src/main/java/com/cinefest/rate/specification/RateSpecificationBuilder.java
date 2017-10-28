package com.cinefest.rate.specification;

import com.cinefest.rate.RateAttr;
import com.cinefest.rate.RateEntity;
import com.cinefest.rate.RateSearchElement;
import com.cinefest.service.util.enumeration.ParamType;
import com.cinefest.service.util.enumeration.QueryOperator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class RateSpecificationBuilder {

  private RateSpecificationHelper specificationHelper = new RateSpecificationHelper();

  public Specifications<RateEntity> buildSpecifications(List<RateSearchElement> searchElements) {
    Specifications<RateEntity> specifications = null;
    for (RateSearchElement search : searchElements) {
      if (specifications == null) {
        specifications = Specifications.where(getSpecification(search.getKey(), search.getValue(), search.getOp()));
      } else {
        specifications.and(getSpecification(search.getKey(), search.getValue(), search.getOp()));
      }
    }
    return specifications;
  }

  private Specification<RateEntity> getSpecification(RateAttr key, String value, QueryOperator op) {
    switch (op) {
      case EQUALS:
        return equalSpecification(key, value);

      case LIKE:
        return likeSpecification(key, value);

      case GREATER:
        return greaterSpecification(key, value);

      case LESS:
        return lessSpecification(key, value);
      default:
        return null;
    }
  }

  private Specification<RateEntity> lessSpecification(RateAttr key, String value) {
    return specificationHelper.lessThan(key.entityAttr, LocalDate.parse(value));
  }

  private Specification<RateEntity> greaterSpecification(RateAttr key, String value) {
    return specificationHelper.greaterThan(key.entityAttr, LocalDate.parse(value));
  }

  private Specification<RateEntity> likeSpecification(RateAttr key, String value) {
    return specificationHelper.like(key.entityAttr, value);
  }

  private Specification<RateEntity> equalSpecification(RateAttr key, String value) {
    if (ParamType.STRING.equals(key.type)) {
      return specificationHelper.equal(key.entityAttr, value);
    }
    if (ParamType.DATE.equals(key.type)) {
      return specificationHelper.equal(key.entityAttr, LocalDate.parse(value));
    }
    return null;
  }
}
