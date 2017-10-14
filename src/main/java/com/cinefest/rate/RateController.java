package com.cinefest.rate;

import com.cinefest.pojo.RateVO;
import com.cinefest.rest.params.SearchCriteria;
import com.cinefest.rest.util.converter.PagingAndSortingParamsConverter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

import static com.cinefest.rate.RateEndpoints.RATES;
import static com.cinefest.rate.RateEndpoints.RATES_BY_ID;

@RestController("/rates")
public class RateController {

  private RateService rateService;
  private PagingAndSortingParamsConverter pagingAndSortingParamsConverter;

  public RateController(RateService rateService) {
    this.rateService = rateService;
  }

  @RequestMapping(method = RequestMethod.GET, value = RATES, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<RateVO> getRates(@RequestParam(required = false) Map<String, String> params) throws IllegalAccessException {
    SearchCriteria searchCriteria = null;
    // TODO: Implement
    return rateService.getAll(searchCriteria);
  }

  @RequestMapping(method = RequestMethod.GET, value = RATES_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public RateVO getRate(@PathParam("id") long id) {
    return rateService.getOne(id);
  }
}
