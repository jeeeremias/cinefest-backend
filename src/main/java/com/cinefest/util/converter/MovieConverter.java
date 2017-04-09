package com.cinefest.util.converter;

import com.cinefest.entity.MovieEntity;
import com.cinefest.pojo.dto.MovieDTO;

import java.util.ArrayList;
import java.util.List;

public class MovieConverter {

    public static MovieEntity dtoToEntity(MovieDTO movieDTO) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setCity(movieDTO.getCity());
        movieEntity.setDirector(movieDTO.getDirector());
        movieEntity.setDirectorBiography(movieDTO.getDirectorBiography());
        movieEntity.setDirectorEmail(movieDTO.getDirectorEmail());
        movieEntity.setFullSynopsis(movieDTO.getFullSynopsis());
        movieEntity.setGenre(movieDTO.getGenre());
        movieEntity.setName(movieDTO.getName());
        movieEntity.setIncomeDate(movieDTO.getIncomeDate());
        movieEntity.setRuntime(movieDTO.getRuntime());
        movieEntity.setScreeningDateTime(movieDTO.getScreeningDateTime());
        movieEntity.setShortSynopsis(movieDTO.getShortSynopsis());
        return movieEntity;
    }

    public static List<MovieDTO> entitiesToDtos(List<MovieEntity> entities) {
        List<MovieDTO> dtos = new ArrayList<>();
        entities.forEach(e -> dtos.add(entityToDto(e)));
        return dtos;
    }

    public static MovieDTO entityToDto(MovieEntity movieEntity) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setCity(movieEntity.getCity());
        movieDTO.setDirector(movieEntity.getDirector());
        movieDTO.setDirectorBiography(movieEntity.getDirectorBiography());
        movieDTO.setDirectorEmail(movieEntity.getDirectorEmail());
        movieDTO.setFullSynopsis(movieEntity.getFullSynopsis());
        movieDTO.setGenre(movieEntity.getGenre());
        movieDTO.setName(movieEntity.getName());
        movieDTO.setIncomeDate(movieEntity.getIncomeDate());
        movieDTO.setRuntime(movieEntity.getRuntime());
        movieDTO.setScreeningDateTime(movieEntity.getScreeningDateTime());
        movieDTO.setShortSynopsis(movieEntity.getShortSynopsis());
        return movieDTO;
    }
}
