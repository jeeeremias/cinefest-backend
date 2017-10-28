package com.cinefest.movie;

import com.cinefest.pojo.MovieVO;

import java.util.List;
import java.util.stream.Collectors;

public class MovieConverter {

  private MovieConverter() {
    throw new IllegalAccessError("Utility class " + this.getClass().getName() + "cannot be instantiated");
  }

  public static MovieEntity toEntity(MovieVO vo) {
    MovieEntity entity = new MovieEntity();
    entity.setId(vo.getId());
    entity.setCity(vo.getCity());
    entity.setState(vo.getState());
    entity.setDirector(vo.getDirector());
    entity.setDirectorBiography(vo.getDirectorBiography());
    entity.setDirectorEmail(vo.getDirectorEmail());
    entity.setSynopsis(vo.getSynopsis());
    entity.setGenre(vo.getGenre());
    entity.setName(vo.getName());
    entity.setIncomeDate(vo.getIncomeDate());
    entity.setRuntime(vo.getRuntime());
    entity.setScreeningDateTime(vo.getScreeningDateTime());
    entity.setType(vo.getType());
    return entity;
  }

  public static MovieVO toVO(MovieEntity entity) {
    MovieVO vo = new MovieVO();
    vo.setId(entity.getId());
    vo.setCity(entity.getCity());
    vo.setState(entity.getState());
    vo.setDirector(entity.getDirector());
    vo.setDirectorBiography(entity.getDirectorBiography());
    vo.setDirectorEmail(entity.getDirectorEmail());
    vo.setSynopsis(entity.getSynopsis());
    vo.setGenre(entity.getGenre());
    vo.setName(entity.getName());
    vo.setIncomeDate(entity.getIncomeDate());
    vo.setRuntime(entity.getRuntime());
    vo.setScreeningDateTime(entity.getScreeningDateTime());
    vo.setType(entity.getType());
    return vo;
  }

  public static List<MovieVO> toVos(List<MovieEntity> entities) {
    return entities.stream()
      .map(MovieConverter::toVO).collect(Collectors.toList());
  }

}
