package com.cinefest.rest.params;

import java.util.List;

public class PagingAndSortingParams {
  int page;
  int size;
  List<String> sort;

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public List<String> getSort() {
    return sort;
  }

  public void setSort(List<String> sort) {
    this.sort = sort;
  }
}
