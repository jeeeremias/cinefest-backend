package com.cinefest.movie.impl;

import com.cinefest.movie.MovieConverter;
import com.cinefest.movie.MovieEntity;
import com.cinefest.movie.MovieRepository;
import com.cinefest.movie.MovieService;
import com.cinefest.movie.MovieSearchElement;
import com.cinefest.rest.params.PagingAndSortingParams;
import com.cinefest.rest.params.SearchCriteria;
import com.cinefest.pojo.MovieVO;
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
import static com.cinefest.movie.MovieConverter.entityToVO;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

  @Autowired
  MovieRepository movieRepository;

  @Autowired
  VoteRepository voteRepository;

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

  @Override
  public MovieVO update(MovieVO movieVO, long id) {
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
    return new PageRequest(params.getPage(), params.getSize(), sort);
  }

}
