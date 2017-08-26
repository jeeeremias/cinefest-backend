package com.cinefest.specification;

import com.cinefest.entity.MovieEntity;
import com.cinefest.pojo.params.MovieQueryCriteria;
import com.cinefest.util.converter.ParamConverter;
import com.cinefest.util.enumeration.MovieAttr;
import com.cinefest.util.enumeration.MovieType;
import com.cinefest.util.enumeration.ParamType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MovieSpecification implements Specification<MovieEntity> {

  private MovieQueryCriteria criteria;

  public MovieSpecification(MovieQueryCriteria criteria) {
    this.criteria = criteria;
  }

  @Override
  public Predicate toPredicate(Root<MovieEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    switch (criteria.getOp()) {
      case EQUALS:
        return getEqualPredicate(root, builder);

      case LIKE:
        return getLikePredicate(root, builder);

      case GREATER:
        return getGreaterPredicate(root, builder);

      case LESS:
        return getLessPredicate(root, builder);
    }
    return null;
  }

  private Predicate getEqualPredicate(Root<MovieEntity> root, CriteriaBuilder builder) {
    if (ParamType.STRING.equals(criteria.getOp())) {
      return builder.equal(
        root.get(criteria.getKey().entityAttr), criteria.getValue());
    }
    if (ParamType.CUSTOM.equals(criteria.getOp()) && MovieAttr.TYPE.equals(criteria.getOp())) {
      return builder.equal(
        root.get(criteria.getKey().entityAttr), MovieType.fromDesc(criteria.getValue()));
    }
    return null;
  }

  private Predicate getLikePredicate(Root<MovieEntity> root, CriteriaBuilder builder) {
    if (ParamType.STRING.equals(criteria.getOp())) {
      return builder.like(root.get(criteria.getKey().entityAttr), "%" + criteria.getValue() + "%");
    }
    return null;
  }

  private Predicate getGreaterPredicate(Root<MovieEntity> root, CriteriaBuilder builder) {
    if (ParamType.DATE.equals(criteria.getKey().type)) {
      return builder.greaterThanOrEqualTo(
        root.get(criteria.getKey().entityAttr), ParamConverter.toDate(criteria.getValue()));
    }
    return null;
  }

  private Predicate getLessPredicate(Root<MovieEntity> root, CriteriaBuilder builder) {
    if (ParamType.DATE.equals(criteria.getKey().type)) {
      return builder.greaterThanOrEqualTo(
        root.get(criteria.getKey().entityAttr), ParamConverter.toDate(criteria.getValue()));
    }
    if (ParamType.CUSTOM.equals(criteria.getOp()) && MovieAttr.TYPE.equals(criteria.getOp())) {
      return builder.greaterThanOrEqualTo(
        root.get(criteria.getKey().entityAttr), MovieType.fromDesc(criteria.getValue()));
    }
    return null;
  }
}
