package com.cinefest.movie.impl;

import com.cinefest.movie.MovieEntity;
import com.cinefest.movie.MovieRepository;
import com.cinefest.movie.MovieSearchElement;
import com.cinefest.movie.MovieService;
import com.cinefest.movie.specification.MovieSpecificationConverter;
import com.cinefest.pojo.MovieVO;
import com.cinefest.rest.params.PagingAndSortingParams;
import com.cinefest.rest.params.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.cinefest.movie.MovieConverter.*;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

  @Autowired
  MovieRepository movieRepository;

  @Override
  public List<MovieVO> getAll(SearchCriteria<MovieSearchElement> searchCriteria) {
    if (searchCriteria == null) {
      return entitiesToVos(movieRepository.findAll());
    }

    Specifications specifications = null;
    PageRequest pageRequest = null;
    if (searchCriteria.getPagingAndSortingParams() != null) {
      pageRequest = createPageRequest(searchCriteria.getPagingAndSortingParams());
    }
    if (searchCriteria.getSearches() != null) {
      specifications = MovieSpecificationConverter.buildSpecifications(searchCriteria.getSearches());
    }

    if (pageRequest != null && specifications != null) {
      return entitiesToVos(movieRepository.findAll(specifications, pageRequest).getContent());
    } else if (pageRequest != null) {
      return entitiesToVos(movieRepository.findAll(pageRequest).getContent());
    } else {
      return entitiesToVos(movieRepository.findAll(specifications));
    }
  }

  @Override
  public MovieVO getOne(long id) {
    return entityToVO(movieRepository.findOne(id));
  }

  @Override
  public MovieVO create(MovieVO movieVO) {
    MovieEntity movieEntity = voToEntity(movieVO);
    return entityToVO(save(movieEntity));
  }

  @Override
  public MovieVO update(long id, MovieVO movieVO) {
    MovieEntity movieEntity = movieRepository.findOne(id);
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
    if (movieVO.getFullSynopsis() != null) {
      movieEntity.setFullSynopsis(movieVO.getFullSynopsis());
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
    if (movieVO.getShortSynopsis() != null) {
      movieEntity.setShortSynopsis(movieVO.getShortSynopsis());
    }
    if (movieVO.getState() != null) {
      movieEntity.setState(movieVO.getState());
    }
    if (movieVO.getType() != null) {
      movieEntity.setType(movieVO.getType());
    }
    return entityToVO(save(movieEntity));
  }

  private MovieEntity save(MovieEntity movieEntity) {
    return movieRepository.save(movieEntity);
  }

  private PageRequest createPageRequest(PagingAndSortingParams params) {
    Sort.Direction direction;
    List<Sort.Order> orders = new ArrayList<>();
    String prop;
    for (String param : params.getSort()) {
      if (param.startsWith("-")) {
        direction = Sort.Direction.DESC;
        prop = param.substring(1);
      } else {
        direction = Sort.Direction.ASC;
        prop = param;
      }
      orders.add(new Sort.Order(direction, prop));
    }
    Sort sort = new Sort(orders);
    return new PageRequest(params.getPage(), params.getSize(), sort);
  }

}
