package com.cinefest.util.converter;

import com.cinefest.entity.MovieEntity;
import com.cinefest.pojo.dto.MovieDTO;

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
}
