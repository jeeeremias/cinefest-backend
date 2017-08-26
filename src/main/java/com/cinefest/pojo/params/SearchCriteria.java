package com.cinefest.pojo.params;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteria {

  private PagingAndSortingParams pagingAndSortingParams;
  private List<Specification> specifications;

  public PagingAndSortingParams getPagingAndSortingParams() {
    return pagingAndSortingParams;
  }

  public void setPagingAndSortingParams(PagingAndSortingParams pagingAndSortingParams) {
    this.pagingAndSortingParams = pagingAndSortingParams;
  }

  public List<Specification> getSpecifications() {
    return specifications;
  }

  public void setSpecifications(List<Specification> specifications) {
    this.specifications = specifications;
  }

  public void addSpecification(Specification specification) {
    if (specifications == null) {
      specifications = new ArrayList<>();
    }
    specifications.add(specification);
  }
}
