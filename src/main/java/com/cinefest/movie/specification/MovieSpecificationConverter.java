package com.cinefest.movie.specification;

import com.cinefest.movie.MovieEntity;
import com.cinefest.movie.MovieSearchElement;
import com.cinefest.movie.enumeration.MovieAttr;
import com.cinefest.movie.enumeration.MovieType;
import com.cinefest.util.enumeration.ParamType;
import com.cinefest.util.enumeration.QueryOperator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.time.LocalDate;
import java.util.List;

public class MovieSpecificationConverter {

  public static Specifications buildSpecifications(List<MovieSearchElement> searchElements) {
    Specifications specifications = null;
    for (MovieSearchElement search : searchElements) {
      if (specifications == null) {
        specifications = Specifications.where(getSpecification(search.getKey(), search.getValue(), search.getOp()));
      } else {
        specifications.and(getSpecification(search.getKey(), search.getValue(), search.getOp()));
      }
    }
    return specifications;
  }

  private static Specification<MovieEntity> getSpecification(MovieAttr key, String value, QueryOperator op) {
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

  private static Specification<MovieEntity> lessSpecification(MovieAttr key, String value) {
    return MovieSpecificationHelper.lessThan(key.entityAttr, LocalDate.parse(value));
  }

  private static Specification<MovieEntity> greaterSpecification(MovieAttr key, String value) {
    return MovieSpecificationHelper.greaterThan(key.entityAttr, LocalDate.parse(value));
  }

  private static Specification<MovieEntity> likeSpecification(MovieAttr key, String value) {
    return MovieSpecificationHelper.like(key.entityAttr, value);
  }

  private static Specification<MovieEntity> equalSpecification(MovieAttr key, String value) {
    if (ParamType.STRING.equals(key.type)) {
      return MovieSpecificationHelper.equal(key.entityAttr, value);
    }
    if (ParamType.CUSTOM.equals(key.type)) {
      return MovieSpecificationHelper.equal(MovieType.fromDesc(value));
    }
    return null;
  }
}
