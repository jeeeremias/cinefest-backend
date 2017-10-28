package com.cinefest.photo;

import com.cinefest.movie.MovieEntity;
import com.cinefest.pojo.PhotoVO;

import java.util.List;
import java.util.stream.Collectors;

public class PhotoConverter {

  public static PhotoVO toVo(PhotoEntity entity) {
    PhotoVO vo = new PhotoVO();
    vo.setMain(entity.isMain());
    vo.setSource(entity.getSource());
    return vo;
  }

  public static PhotoEntity toEntity(PhotoVO vo, MovieEntity movieEntity) {
    PhotoEntity entity = new PhotoEntity();
    entity.setMain(vo.isMain());
    entity.setSource(vo.getSource());
    entity.setMovie(movieEntity);
    return entity;
  }

  public static List<PhotoEntity> toEntities(List<PhotoVO> vos, MovieEntity movieEntity) {
    return vos.stream()
      .map(vo -> toEntity(vo, movieEntity))
      .collect(Collectors.toList());
  }

  public static List<PhotoVO> toVos(List<PhotoEntity> entities) {
    return entities.stream()
      .map(PhotoConverter::toVo)
      .collect(Collectors.toList());
  }
}
