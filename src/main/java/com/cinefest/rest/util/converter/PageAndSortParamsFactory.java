package com.cinefest.rest.util.converter;

import com.cinefest.rest.params.PageAndSortParams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

public class PageAndSortParamsFactory {

  private static final String PAGE_PARAM = "page";
  private static final String SIZE_PARAM = "size";
  private static final String SORT_PARAM = "sort";
  private static final int DEFAULT_PAGE = 0;
  private static final int DEFAULT_SIZE = 100;

  private PageAndSortParamsFactory() {}

  public static PageAndSortParams fromParams(Map<String, String> params) {
    PageAndSortParams pageAndSortParams = new PageAndSortParams();

    pageAndSortParams.setPage(getNaturalAttr(params, PAGE_PARAM, DEFAULT_PAGE));
    pageAndSortParams.setSize(getNaturalAttr(params, SIZE_PARAM, DEFAULT_SIZE));
    pageAndSortParams.setSort(getListAttr(params, SORT_PARAM));

    return pageAndSortParams;
  }

  public static PageAndSortParams createDefault() {
    PageAndSortParams pageAndSortParams = new PageAndSortParams();
    pageAndSortParams.setPage(DEFAULT_PAGE);
    pageAndSortParams.setSize(DEFAULT_SIZE);
    pageAndSortParams.setSort(emptyList());
    return pageAndSortParams;
  }


  private static List<String> getListAttr(Map<String, String> params, String attr) {
    String value = params.get(attr);
    if (value == null) {
      return emptyList();
    }
    return Arrays.asList(value.split(","));
  }

  private static int getNaturalAttr(Map<String, String> params, String attr, int defaultValue) {
    int num = getIntegerAttr(params, attr, defaultValue);

    if (num < 0) {
      // TODO: Properly exception here
    }

    return num;
  }

  private static int getIntegerAttr(Map<String, String> params, String attr, int defaultValue) {
    String value = params.get(attr);
    if (value == null) {
      return defaultValue;
    }

    Integer num = Integer.valueOf(value);
    if (num == null) {
      // TODO: Properly exception here
    }

    return num;
  }
}
