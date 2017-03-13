package com.cinefest.specification;

import com.cinefest.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MovieSpecification implements Specification<MovieEntity> {

    @Override
    public Predicate toPredicate(Root<MovieEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
