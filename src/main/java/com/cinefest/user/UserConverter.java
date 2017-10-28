package com.cinefest.user;

import com.cinefest.pojo.PublicUserVO;
import com.cinefest.pojo.UserVO;

public class UserConverter {

  public static UserVO toVo(UserEntity entity) {
    UserVO vo = new UserVO();
    vo.setId(entity.getId());
    vo.setBornDate(entity.getBornDate());
    vo.setCellPhone(entity.getCellPhone());
    vo.setEmail(entity.getEmail());
    vo.setFirstName(entity.getFirstName());
    vo.setLastName(entity.getLastName());
    vo.setPrivateEmail(entity.isPublicEmail());
    return vo;
  }

  public static PublicUserVO toPublicVo(UserEntity entity) {
    PublicUserVO vo = new PublicUserVO();
    if (entity.isPublicEmail()) {
      vo.setEmail(entity.getEmail());
    }
    vo.setFirstName(entity.getFirstName());
    vo.setLastName(entity.getLastName());
    return vo;
  }
}
