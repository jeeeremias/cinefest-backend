package com.cinefest.specification;

import com.cinefest.entity.MovieEntity;
import com.cinefest.pojo.criteria.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MovieSpecification implements Specification<MovieEntity> {

    private SearchCriteria criteria;

    public MovieSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<MovieEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (criteria.getValue().startsWith(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue());
        }
        if (criteria.getValue().startsWith("<")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue());
        }
        if (criteria.getValue().startsWith(":")) {
            return criteriaBuilder.equal(
                    root.get(criteria.getKey()), criteria.getValue().substring(1));
        }
        return criteriaBuilder.like(
                root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
    }
}
