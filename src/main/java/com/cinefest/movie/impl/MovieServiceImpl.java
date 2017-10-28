package com.cinefest.movie.impl;

import com.cinefest.movie.MovieEntity;
import com.cinefest.movie.MovieRepository;
import com.cinefest.movie.MovieSearchElement;
import com.cinefest.movie.MovieService;
import com.cinefest.movie.specification.MovieSpecificationBuilder;
import com.cinefest.pojo.MovieVO;
import com.cinefest.rest.params.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.cinefest.movie.MovieConverter.*;
import static com.cinefest.service.util.converter.PageRequestConverter.toPageRequest;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

  private MovieRepository movieRepository;
  private MovieSpecificationBuilder specificationBuilder;

  @Autowired
  public MovieServiceImpl(MovieRepository movieRepository, MovieSpecificationBuilder specificationBuilder) {
    this.movieRepository = movieRepository;
    this.specificationBuilder = specificationBuilder;
  }

  @Override
  public List<MovieVO> getAll(SearchCriteria<MovieSearchElement> searchCriteria) {
    Specifications<MovieEntity> specifications = null;
    PageRequest pageRequest = null;
    if (searchCriteria.getPageAndSortParams() != null) {
      pageRequest = toPageRequest(searchCriteria.getPageAndSortParams());
    }
    if (searchCriteria.getSearches() != null) {
      specifications = specificationBuilder.buildSpecifications(searchCriteria.getSearches());
    }

    if (pageRequest != null && specifications != null) {
      return toVos(movieRepository.findAll(specifications, pageRequest).getContent());
    } else if (pageRequest != null) {
      return toVos(movieRepository.findAll(pageRequest).getContent());
    } else {
      return toVos(movieRepository.findAll(specifications));
    }
  }

  @Override
  public MovieVO getOne(long id) {
    return toVO(movieRepository.getOne(id));
  }

  @Override
  public MovieVO create(MovieVO movieVO) {
    MovieEntity movieEntity = toEntity(movieVO);
    return toVO(save(movieEntity));
  }

  @Override
  public MovieVO update(long id, MovieVO movieVO) {
    MovieEntity movieEntity = movieRepository.getOne(id);
    if (movieVO.getCity() != null) {
      movieEntity.setCity(movieVO.getCity());
    }
    if (movieVO.getDirector() != null) {
      movieEntity.setDirector(movieVO.getDirector());
    }
    if (movieVO.getDirectorBiography() != null) {
      movieEntity.setDirectorBiography(movieVO.getDirectorBiography());
    }
    if (movieVO.getDirectorEmail() != null) {
      movieEntity.setDirectorEmail(movieVO.getDirectorEmail());
    }
    if (movieVO.getSynopsis() != null) {
      movieEntity.setSynopsis(movieVO.getSynopsis());
    }
    if (movieVO.getGenre() != null) {
      movieEntity.setGenre(movieVO.getGenre());
    }
    if (movieVO.getIncomeDate() != null) {
      movieEntity.setIncomeDate(movieVO.getIncomeDate());
    }
    if (movieVO.getName() != null) {
      movieEntity.setName(movieVO.getName());
    }
    if (movieVO.getRuntime() != null) {
      movieEntity.setRuntime(movieVO.getRuntime());
    }
    if (movieVO.getScreeningDateTime() != null) {
      movieEntity.setScreeningDateTime(movieVO.getScreeningDateTime());
    }
    if (movieVO.getState() != null) {
      movieEntity.setState(movieVO.getState());
    }
    if (movieVO.getType() != null) {
      movieEntity.setType(movieVO.getType());
    }
    return toVO(save(movieEntity));
  }

  @Override
  public void delete(long id) {
    movieRepository.deleteById(id);
  }

  private MovieEntity save(MovieEntity movieEntity) {
    return movieRepository.save(movieEntity);
  }

}
