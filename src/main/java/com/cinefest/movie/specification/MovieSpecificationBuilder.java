package com.cinefest.movie.specification;

import com.cinefest.movie.MovieEntity;
import com.cinefest.movie.MovieSearchElement;
import com.cinefest.movie.enumeration.MovieAttr;
import com.cinefest.movie.enumeration.MovieType;
import com.cinefest.service.util.enumeration.ParamType;
import com.cinefest.service.util.enumeration.QueryOperator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MovieSpecificationBuilder {

  private MovieSpecificationHelper specificationHelper = new MovieSpecificationHelper();

  public Specifications<MovieEntity> buildSpecifications(List<MovieSearchElement> searchElements) {
    Specifications<MovieEntity> specifications = null;
    for (MovieSearchElement search : searchElements) {
      if (specifications == null) {
        specifications = Specifications.where(getSpecification(search.getKey(), search.getValue(), search.getOp()));
      } else {
        specifications.and(getSpecification(search.getKey(), search.getValue(), search.getOp()));
      }
    }
    return specifications;
  }

  private Specification<MovieEntity> getSpecification(MovieAttr key, String value, QueryOperator op) {
    switch (op) {
      case EQUALS:
        return equalSpecification(key, value);

      case LIKE:
        return likeSpecification(key, value);

      case GREATER:
        return greaterSpecification(key, value);

      case LESS:
        return lessSpecification(key, value);
    }
    return null;
  }

  private Specification<MovieEntity> lessSpecification(MovieAttr key, String value) {
    return specificationHelper.lessThan(key.entityAttr, LocalDate.parse(value));
  }

  private Specification<MovieEntity> greaterSpecification(MovieAttr key, String value) {
    return specificationHelper.greaterThan(key.entityAttr, LocalDate.parse(value));
  }

  private Specification<MovieEntity> likeSpecification(MovieAttr key, String value) {
    return specificationHelper.like(key.entityAttr, value);
  }

  private Specification<MovieEntity> equalSpecification(MovieAttr key, String value) {
    if (ParamType.STRING.equals(key.type)) {
      return specificationHelper.equal(key.entityAttr, value);
    }
    if (ParamType.DATE.equals(key.type)) {
      return specificationHelper.equal(key.entityAttr, LocalDate.parse(value));
    }
    if (ParamType.CUSTOM.equals(key.type)) {
      return specificationHelper.equal(MovieType.fromDesc(value));
    }
    return null;
  }

}
