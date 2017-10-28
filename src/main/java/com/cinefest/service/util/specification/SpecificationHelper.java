package com.cinefest.service.util.specification;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SpecificationHelper<T> {

  public Specification<T> equal(String key, String value) {
    return (root, query, builder) -> builder.equal(root.get(key), value);
  }

  public Specification<T> equal(String key, LocalDate value) {
    return (root, query, builder) -> builder.equal(root.get(key), value);
  }

  public Specification<T> equal(String key, LocalDateTime value) {
    return (root, query, builder) -> builder.equal(root.get(key), value);
  }

  public Specification<T> like(String key, String value) {
    return (root, query, builder) -> builder.equal(root.get(key), value);
  }

  public Specification<T> greaterThan(String key, LocalDate value) {
    return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get(key), value);
  }

  public Specification<T> greaterThan(String key, LocalDateTime value) {
    return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get(key), value);
  }

  public Specification<T> lessThan(String key, LocalDate value) {
    return (root, query, builder) -> builder.lessThan(root.get(key), value);
  }

  public Specification<T> lessThan(String key, LocalDateTime value) {
    return (root, query, builder) -> builder.lessThan(root.get(key), value);
  }
}
