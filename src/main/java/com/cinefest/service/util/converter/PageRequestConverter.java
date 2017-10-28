package com.cinefest.service.util.converter;

import com.cinefest.rest.params.PageAndSortParams;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class PageRequestConverter {

  private PageRequestConverter() {}

  public static PageRequest toPageRequest(@NotNull PageAndSortParams params) {
    Sort.Direction direction;
    List<Sort.Order> orders = new ArrayList<>();
    String prop;
    for (String param : params.getSort()) {
      if (param.startsWith("-")) {
        direction = Sort.Direction.DESC;
        prop = param.substring(1);
      } else {
        direction = Sort.Direction.ASC;
        prop = param;
      }
      orders.add(new Sort.Order(direction, prop));
    }
    Sort sort = new Sort(orders);
    return new PageRequest(params.getPage(), params.getSize(), sort);
  }
}
