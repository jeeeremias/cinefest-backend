package com.cinefest.rate;

import com.cinefest.pojo.RateVO;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static com.cinefest.user.UserConverter.toPublicVo;

public class RateConverter {

  private RateConverter() {}

  public static RateVO toVo(@NotNull RateEntity entity) {
    RateVO vo = new RateVO();
    vo.setId(entity.getId());
    vo.setDateTime(entity.getDateTime());
    vo.setRate(entity.getRate());
    vo.setPublicUserVO(toPublicVo(entity.getUser()));
    return vo;
  }

  public static List<RateVO> toVos(@NotNull List<RateEntity> entities) {
    return entities.stream()
      .map(e -> toVo(e))
      .collect(Collectors.toList());
  }
}
