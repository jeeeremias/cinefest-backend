package com.cinefest.movie.impl;

import com.cinefest.movie.MovieConverter;
import com.cinefest.movie.MovieEntity;
import com.cinefest.movie.MovieRepository;
import com.cinefest.movie.MovieService;
import com.cinefest.movie.pojo.MovieSearchElement;
import com.cinefest.rest.params.PagingAndSortingParams;
import com.cinefest.rest.params.SearchCriteria;
import com.cinefest.movie.pojo.MovieVO;
import com.cinefest.repository.VoteRepository;
import com.cinefest.movie.specification.MovieSpecificationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.cinefest.movie.MovieConverter.entitiesToVos;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

  @Autowired
  MovieRepository movieRepository;

  @Autowired
  VoteRepository voteRepository;

  @Override
  public List<MovieVO> getAll(SearchCriteria<MovieSearchElement> searchCriteria) {
    Specifications specifications = null;
    PageRequest pageRequest = null;
    List<MovieVO> movies;
    if (searchCriteria.getPagingAndSortingParams() != null) {
      pageRequest = createPageRequest(searchCriteria.getPagingAndSortingParams());
    }
    if (searchCriteria.getSearches() != null) {
      specifications = MovieSpecificationConverter.buildSpecifications(searchCriteria.getSearches());
    }

    if (pageRequest != null && searchCriteria != null) {
      movies = entitiesToVos(movieRepository.findAll(specifications, pageRequest).getContent());
    } else if (pageRequest != null) {
      movies = entitiesToVos(movieRepository.findAll(pageRequest).getContent());
    } else {
      movies = entitiesToVos(movieRepository.findAll(specifications));
    }
    return movies;
  }

  @Override
  public MovieVO getOne(long id) {
    return MovieConverter.entityToVO(movieRepository.findOne(id));
  }

  public String votos(String dia) {
    List<MovieEntity> movieEntities = getByDay(dia);
    StringBuilder sb = new StringBuilder("Codigo, MovieEntity, Votos, (%)\n");
    float total = voteRepository.countByDateTime(dia);

    for (MovieEntity movieEntity : movieEntities) {
      sb.append(movieEntity.getId() + ",");
      sb.append(movieEntity.getName() + ",");
      sb.append(movieEntity.getVotes().size() + ",");
      sb.append(((100.0 * movieEntity.getVotes().size()) / total) + ",");
      sb.append("\n");
    }
    sb.append(",,,\n,,Total Votos," + total + "\n");
    return sb.toString();
  }

  @Override
  public MovieVO create(MovieVO movieVO) {
    MovieEntity movieEntity = MovieConverter.voToEntity(movieVO);
    return MovieConverter.entityToVO(save(movieEntity));
  }

  public MovieEntity save(MovieEntity movieEntity) {
    return movieRepository.save(movieEntity);
  }

  public List<MovieEntity> getByDay(String dataExibicao) {
    List<MovieEntity> movieEntities = movieRepository.findByScreeningDateTime(dataExibicao, new Sort(Sort.Direction.ASC, "name"));
    if (dataExibicao.equals("15/02") || dataExibicao.equals("16/02") || dataExibicao.equals("17/02") || dataExibicao.equals("18/02") || dataExibicao.equals("19/02")) {
      if (movieEntities != null) {
        movieEntities.addAll(movieRepository.findByScreeningDateTime("15 a 19/02", new Sort(Sort.Direction.ASC, "name")));
      } else {
        movieEntities = movieRepository.findByScreeningDateTime("15 a 19/02", new Sort(Sort.Direction.ASC, "name"));
      }
    }
    return movieEntities;
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
    PageRequest pageRequest = new PageRequest(params.getPage(), params.getSize(), sort);
    return pageRequest;
  }

}
