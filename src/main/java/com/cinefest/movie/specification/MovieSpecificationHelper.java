package com.cinefest.movie.specification;

import com.cinefest.movie.MovieEntity;
import com.cinefest.movie.enumeration.MovieType;
import com.cinefest.service.util.specification.SpecificationHelper;
import org.springframework.data.jpa.domain.Specification;

import static com.cinefest.movie.enumeration.MovieAttr.TYPE;

public class MovieSpecificationHelper extends SpecificationHelper<MovieEntity> {

  public Specification<MovieEntity> equal(MovieType type) {
    return (root, query, builder) -> builder.equal(root.get(TYPE.entityAttr), type);
  }
}
