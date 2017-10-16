package com.cinefest.rest.params;

import com.cinefest.search.SearchElement;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteria<T extends SearchElement> {

  private PagingAndSortingParams pagingAndSortingParams;
  private List<T> searches;

  public PagingAndSortingParams getPagingAndSortingParams() {
    return pagingAndSortingParams;
  }

  public void setPagingAndSortingParams(PagingAndSortingParams pagingAndSortingParams) {
    this.pagingAndSortingParams = pagingAndSortingParams;
  }

  public List<T> getSearches() {
    return searches;
  }

  public void addSearch(T search) {
    if (searches == null) {
      searches = new ArrayList<>();
    }
    searches.add(search);
  }
}
