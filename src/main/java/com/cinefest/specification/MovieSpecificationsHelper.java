package com.cinefest.specification;

import com.cinefest.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecificationsHelper {

  public static Specification<MovieEntity> equalsString(String key, String value) {
    return (root, query, builder) -> builder.equal(root.get(key), value);
  }
}
