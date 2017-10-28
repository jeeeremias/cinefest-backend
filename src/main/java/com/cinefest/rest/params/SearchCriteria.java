package com.cinefest.rest.params;

import com.cinefest.search.SearchElement;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteria<T extends SearchElement> {

  private PageAndSortParams pageAndSortParams;
  private List<T> searches;

  public PageAndSortParams getPageAndSortParams() {
    return pageAndSortParams;
  }

  public void setPageAndSortParams(PageAndSortParams pageAndSortParams) {
    this.pageAndSortParams = pageAndSortParams;
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
