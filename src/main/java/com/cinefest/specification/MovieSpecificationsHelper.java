package com.cinefest.specification;

import com.cinefest.entity.MovieEntity;
import com.cinefest.util.enumeration.MovieType;
import org.springframework.data.jpa.domain.Specification;

import static com.cinefest.util.enumeration.MovieAttr.TYPE;

public class MovieSpecificationsHelper {

  public static Specification<MovieEntity> equal(String key, String value) {
    return (root, query, builder) -> builder.equal(root.get(key), value);
  }

  public static Specification<MovieEntity> equal(String key, MovieType type) {
    return (root, query, builder) -> builder.equal(root.get(TYPE.entityAttr), type);
  }

  public static Specification<MovieEntity> like(String key, String value) {
    return (root, query, builder) -> builder.equal(root.get(key), value);
  }
}
