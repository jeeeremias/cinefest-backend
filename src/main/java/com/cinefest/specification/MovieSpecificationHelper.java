package com.cinefest.specification;

import com.cinefest.entity.MovieEntity;
import com.cinefest.util.enumeration.MovieType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.cinefest.util.enumeration.MovieAttr.TYPE;

public class MovieSpecificationHelper {

  public static Specification<MovieEntity> equal(String key, String value) {
    return (root, query, builder) -> builder.equal(root.get(key), value);
  }

  public static Specification<MovieEntity> equal(MovieType type) {
    return (root, query, builder) -> builder.equal(root.get(TYPE.entityAttr), type);
  }

  public static Specification<MovieEntity> like(String key, String value) {
    return (root, query, builder) -> builder.equal(root.get(key), value);
  }

  public static Specification<MovieEntity> greaterThan(String key, LocalDate value) {
    return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get(key), value);
  }

  public static Specification<MovieEntity> greaterThan(String key, LocalDateTime value) {
    return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get(key), value);
  }

  public static Specification<MovieEntity> lessThan(String key, LocalDate value) {
    return (root, query, builder) -> builder.lessThan(root.get(key), value);
  }

  public static Specification<MovieEntity> lessThan(String key, LocalDateTime value) {
    return (root, query, builder) -> builder.lessThan(root.get(key), value);
  }
}
