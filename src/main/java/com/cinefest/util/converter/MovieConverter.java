package com.cinefest.util.converter;

import com.cinefest.entity.MovieEntity;
import com.cinefest.pojo.dto.MovieDTO;
import com.cinefest.pojo.vo.MovieVO;

import java.util.List;
import java.util.stream.Collectors;

public class MovieConverter {

  private MovieConverter() {
    throw new IllegalAccessError("Utility class " + this.getClass().getName() + "cannot be instantiated");
  }

  public static MovieEntity voToEntity(MovieVO vo) {
    MovieEntity entity = new MovieEntity();
    entity.setId(vo.getId());
    entity.setCity(vo.getCity());
    entity.setDirector(vo.getDirector());
    entity.setDirectorBiography(vo.getDirectorBiography());
    entity.setDirectorEmail(vo.getDirectorEmail());
    entity.setFullSynopsis(vo.getFullSynopsis());
    entity.setGenre(vo.getGenre());
    entity.setName(vo.getName());
    entity.setIncomeDate(vo.getIncomeDate());
    entity.setRuntime(vo.getRuntime());
    entity.setScreeningDateTime(vo.getScreeningDateTime());
    entity.setShortSynopsis(vo.getShortSynopsis());
    return entity;
  }

  public static MovieVO entityToVO(MovieEntity entity) {
    MovieVO vo = new MovieVO();
    vo.setId(entity.getId());
    vo.setCity(entity.getCity());
    vo.setDirector(entity.getDirector());
    vo.setDirectorBiography(entity.getDirectorBiography());
    vo.setDirectorEmail(entity.getDirectorEmail());
    vo.setFullSynopsis(entity.getFullSynopsis());
    vo.setGenre(entity.getGenre());
    vo.setName(entity.getName());
    vo.setIncomeDate(entity.getIncomeDate());
    vo.setRuntime(entity.getRuntime());
    vo.setScreeningDateTime(entity.getScreeningDateTime());
    vo.setShortSynopsis(entity.getShortSynopsis());
    return vo;
  }

  public static MovieVO dtoToVO(MovieDTO dtos) {
    MovieVO vo = new MovieVO();
    vo.setCity(dtos.getCity());
    vo.setDirector(dtos.getDirector());
    vo.setDirectorBiography(dtos.getDirectorBiography());
    vo.setDirectorEmail(dtos.getDirectorEmail());
    vo.setFullSynopsis(dtos.getFullSynopsis());
    vo.setGenre(dtos.getGenre());
    vo.setName(dtos.getName());
    vo.setIncomeDate(dtos.getIncomeDate());
    vo.setRuntime(dtos.getRuntime());
    vo.setScreeningDateTime(dtos.getScreeningDateTime());
    vo.setShortSynopsis(dtos.getShortSynopsis());
    return vo;
  }

  public static MovieDTO voToDto(MovieVO vo) {
    MovieDTO dto = new MovieDTO();
    dto.setCity(vo.getCity());
    dto.setDirector(vo.getDirector());
    dto.setDirectorBiography(vo.getDirectorBiography());
    dto.setDirectorEmail(vo.getDirectorEmail());
    dto.setFullSynopsis(vo.getFullSynopsis());
    dto.setGenre(vo.getGenre());
    dto.setName(vo.getName());
    dto.setIncomeDate(vo.getIncomeDate());
    dto.setRuntime(vo.getRuntime());
    dto.setScreeningDateTime(vo.getScreeningDateTime());
    dto.setShortSynopsis(vo.getShortSynopsis());
    return dto;
  }

  public static List<MovieVO> entitiesToVos(List<MovieEntity> entities) {
    return entities.stream()
      .map(MovieConverter::entityToVO).collect(Collectors.toList());
  }

  public static List<MovieDTO> vosToDtos(List<MovieVO> vos) {
    return vos.stream()
      .map(MovieConverter::voToDto).collect(Collectors.toList());
  }

}
