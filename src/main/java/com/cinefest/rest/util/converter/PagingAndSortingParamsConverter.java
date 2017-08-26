package com.cinefest.rest.util.converter;

import com.cinefest.pojo.params.PagingAndSortingParams;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class PagingAndSortingParamsConverter {

  static final String PAGE_PARAM = "page";
  static final String SIZE_PARAM = "size";
  static final String SORT_PARAM = "sort";

  final int defaultPage = 0;
  final int defaultSize = 100;

  public PagingAndSortingParams convertToQueryParam(Map<String, String> params) {
    PagingAndSortingParams pagingAndSortingParams = new PagingAndSortingParams();

    pagingAndSortingParams.setPage(getNaturalAttr(params, PAGE_PARAM, defaultPage));
    pagingAndSortingParams.setSize(getNaturalAttr(params, SIZE_PARAM, defaultSize));
    pagingAndSortingParams.setSort(getListAttr(params, SORT_PARAM));

    return pagingAndSortingParams;
  }

  private List<String> getListAttr(Map<String, String> params, String attr) {
    String value = params.get(attr);
    if (value == null) {
      return null;
    }
    return Arrays.asList(value.split(","));
  }

  private int getNaturalAttr(Map<String, String> params, String attr, int defaultValue) {
    int num = getIntegerAttr(params, attr, defaultValue);

    if (num < 0) {
      // TODO: Properly exception here
    }

    return num;
  }

  private int getIntegerAttr(Map<String, String> params, String attr, int defaultValue) {
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
