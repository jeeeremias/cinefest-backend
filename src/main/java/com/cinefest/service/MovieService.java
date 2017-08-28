package com.cinefest.service;

import com.cinefest.entity.MovieEntity;
import com.cinefest.pojo.params.MovieSearchElement;
import com.cinefest.pojo.params.PagingAndSortingParams;
import com.cinefest.pojo.params.SearchCriteria;
import com.cinefest.pojo.vo.MovieVO;
import com.cinefest.repository.MovieRepository;
import com.cinefest.repository.VoteRepository;
import com.cinefest.specification.MovieSpecificationConverter;
import com.cinefest.specification.MovieSpecificationHelper;
import com.cinefest.util.converter.MovieConverter;
import com.cinefest.util.enumeration.MovieAttr;
import com.cinefest.util.enumeration.MovieType;
import com.cinefest.util.enumeration.ParamType;
import com.cinefest.util.enumeration.QueryOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MovieService {

  @Autowired
  MovieRepository movieRespository;

  @Autowired
  VoteRepository voteRespository;

  public List<MovieVO> getAll(SearchCriteria<MovieSearchElement> searchCriteria) {
    PageRequest pageRequest = createPageRequest(searchCriteria.getPagingAndSortingParams());
    Specifications specifications = MovieSpecificationConverter.createSpecifications(searchCriteria.getSearches());
    return MovieConverter.entitiesToVos(movieRespository.findAll(specifications, pageRequest).getContent());
  }

  public List<MovieEntity> getByDay(String dataExibicao) {
    List<MovieEntity> movieEntities = movieRespository.findByscreeningDateTime(dataExibicao, new Sort(Sort.Direction.ASC, "name"));
    if (dataExibicao.equals("15/02") || dataExibicao.equals("16/02") || dataExibicao.equals("17/02") || dataExibicao.equals("18/02") || dataExibicao.equals("19/02")) {
      if (movieEntities != null) {
        movieEntities.addAll(movieRespository.findByscreeningDateTime("15 a 19/02", new Sort(Sort.Direction.ASC, "name")));
      } else {
        movieEntities = movieRespository.findByscreeningDateTime("15 a 19/02", new Sort(Sort.Direction.ASC, "name"));
      }
    }
    return movieEntities;
  }

  public MovieVO getOne(long id) {
    return MovieConverter.entityToVO(movieRespository.findOne(id));
  }

  public String votos(String dia) {
    List<MovieEntity> movieEntities = getByDay(dia);
    StringBuilder sb = new StringBuilder("Codigo, MovieEntity, Votos, (%)\n");
    float total = voteRespository.countByDateTime(dia);

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

  public MovieEntity newMovie(MovieVO movieVO) {
    MovieEntity movieEntity = MovieConverter.voToEntity(movieVO);
    return save(movieEntity);
  }

  public MovieEntity save(MovieEntity movieEntity) {
    return movieRespository.save(movieEntity);
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
