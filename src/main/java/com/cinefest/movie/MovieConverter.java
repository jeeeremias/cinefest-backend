package com.cinefest.movie;

import com.cinefest.entity.PhotoEntity;
import com.cinefest.pojo.MovieVO;
import com.cinefest.pojo.PhotoVO;

import java.util.ArrayList;
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
    entity.setSynopsis(vo.getSynopsis());
    entity.setGenre(vo.getGenre());
    entity.setName(vo.getName());
    entity.setIncomeDate(vo.getIncomeDate());
    entity.setRuntime(vo.getRuntime());
    entity.setScreeningDateTime(vo.getScreeningDateTime());
    return entity;
  }

  public static MovieVO entityToVO(MovieEntity entity) {
    MovieVO vo = new MovieVO();
    vo.setId(entity.getId());
    vo.setCity(entity.getCity());
    vo.setDirector(entity.getDirector());
    vo.setDirectorBiography(entity.getDirectorBiography());
    vo.setDirectorEmail(entity.getDirectorEmail());
    vo.setSynopsis(entity.getSynopsis());
    vo.setGenre(entity.getGenre());
    vo.setName(entity.getName());
    vo.setIncomeDate(entity.getIncomeDate());
    vo.setRuntime(entity.getRuntime());
    vo.setScreeningDateTime(entity.getScreeningDateTime());
    List<PhotoVO> photos = new ArrayList<>();
    for (PhotoEntity photo : entity.getPhotos()) {
      photos.add(new PhotoVO(photo.getId()));
    }
    vo.setPhotos(photos);
    return vo;
  }

  public static List<MovieVO> entitiesToVos(List<MovieEntity> entities) {
    return entities.stream()
      .map(MovieConverter::entityToVO).collect(Collectors.toList());
  }

}
