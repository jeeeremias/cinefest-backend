package com.cinefest.rate;

import com.cinefest.pojo.RateVO;
import com.cinefest.rest.params.SearchCriteria;
import com.cinefest.rest.util.converter.PagingAndSortingParamsConverter;
import com.cinefest.util.enumeration.QueryOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cinefest.rest.PathEndpoints.RATES;
import static com.cinefest.rest.PathEndpoints.RATE_ID;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
public class RateController {

  private RateService rateService;
  private PagingAndSortingParamsConverter pagingAndSortingParamsConverter;

  @Autowired
  public RateController(RateService rateService, PagingAndSortingParamsConverter pagingAndSortingParamsConverter) {
    this.rateService = rateService;
    this.pagingAndSortingParamsConverter = pagingAndSortingParamsConverter;
  }

  @RequestMapping(method = GET, value = RATES, produces = APPLICATION_JSON_VALUE)
  public List<RateVO> getRates(@RequestParam(required = false) Map<String, String> params) throws IllegalAccessException {
    SearchCriteria searchCriteria = toMovieQuery(params);
    return rateService.getAll(searchCriteria);
  }

  @RequestMapping(method = GET, value = RATE_ID, produces = APPLICATION_JSON_VALUE)
  public RateVO getRate(@PathParam("id") long id) {
    return rateService.getOne(id);
  }

  @ResponseStatus(value = HttpStatus.CREATED)
  @RequestMapping(method = POST, value = RATES, produces = APPLICATION_JSON_VALUE)
  public RateVO newRate(@RequestBody RateVO rate) {
    return rateService.create(rate);
  }

  @RequestMapping(method = PUT, value = RATE_ID, produces = APPLICATION_JSON_VALUE)
  public RateVO updateRate(@PathParam("id") long id, @RequestBody RateVO rate) {
    return rateService.update(id, rate);
  }

  @ResponseStatus(NO_CONTENT)
  @RequestMapping(method = DELETE, value = RATE_ID)
  public void deleteRate(@PathParam("id") long id) throws IllegalAccessException {
    throw new IllegalAccessException();
  }

  private SearchCriteria toMovieQuery(Map<String, String> params) {
    SearchCriteria searchCriteria = new SearchCriteria();
    if (params == null) {
      params = new HashMap<>();
    }
    searchCriteria.setPagingAndSortingParams(pagingAndSortingParamsConverter.convertToQueryParam(params));

    params.entrySet()
      .forEach(e -> {
        RateAttr attrEnum = RateAttr.fromQueryAttr(e.getKey());
        if (attrEnum == null || e.getValue() == null) {
          return;
        }
        if (!attrEnum.searchable) {
          return;
        }
        RateSearchElement searchElem = createCriteria(e.getValue());
        searchElem.setKey(attrEnum);
        searchCriteria.addSearch(searchElem);
      });

    return searchCriteria;
  }

  private RateSearchElement createCriteria(String value) {
    RateSearchElement movieSearchCriteria = new RateSearchElement();

    return Arrays.stream(QueryOperator.values())
      .filter(e -> value.startsWith(e.op))
      .map(e -> {
        movieSearchCriteria.setValue(value.substring(1));
        movieSearchCriteria.setOp(e);
        return movieSearchCriteria;
      })
      .findFirst()
      .orElseGet(() -> {
        movieSearchCriteria.setValue(value);
        movieSearchCriteria.setOp(QueryOperator.EQUALS);
        return movieSearchCriteria;
      });
  }
}
