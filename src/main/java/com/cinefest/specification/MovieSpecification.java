package com.cinefest.specification;

import com.cinefest.entity.MovieEntity;
import com.cinefest.pojo.criteria.SearchCriteria;
import com.cinefest.pojo.params.QueryCriteria;
import com.cinefest.util.enumeration.QueryOperatorEnum;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MovieSpecification implements Specification<MovieEntity> {

    private QueryCriteria queryCriteria;

    public MovieSpecification(QueryCriteria queryCriteria) {
        this.queryCriteria = queryCriteria;
    }

    @Override
    public Predicate toPredicate(Root<MovieEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (QueryOperatorEnum.GREATER.equals(queryCriteria.getOp())) {
            return criteriaBuilder.great;
        }
        if (criteria.getValue().startsWith("<")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue());
        }
        if (criteria.getValue().startsWith(":")) {
            return criteriaBuilder.equal(
                    root.get(queryCriteria.getKey()), queryCriteria.getValue());
        }
        return criteriaBuilder.like(
                root.get(queryCriteria.getKey()), "%" + queryCriteria.getValue() + "%");
    }
}
