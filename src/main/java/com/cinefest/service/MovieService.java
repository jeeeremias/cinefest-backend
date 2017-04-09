package com.cinefest.service;

import com.cinefest.entity.MovieEntity;
import com.cinefest.pojo.dto.MovieDTO;
import com.cinefest.pojo.params.PagingAndSortingParams;
import com.cinefest.pojo.params.QueryCriteria;
import com.cinefest.pojo.params.SearchCriteria;
import com.cinefest.repository.MovieRepository;
import com.cinefest.repository.VoteRepository;
import com.cinefest.specification.MovieSpecification;
import com.cinefest.util.converter.MovieConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MovieService {

    @Autowired
    MovieRepository movieRespository;

    @Autowired
    VoteRepository voteRespository;

    public List<MovieEntity> getAll(SearchCriteria searchCriteria) {
        PageRequest pageRequest = createPageRequest(searchCriteria.getPagingAndSortingParams());
        Specifications specifications = createSpecifications(searchCriteria.getSpecifications());
        return movieRespository.findAll(specifications, pageRequest).getContent();
    }

    public List<MovieEntity> getByDay(String dataExibicao) {
        List<MovieEntity> movieEntities = movieRespository.findByscreeningDateTime(dataExibicao, new Sort(Sort.Direction.ASC, "name"));
        if (dataExibicao.equals("15/02") || dataExibicao.equals("16/02") || dataExibicao.equals("17/02") || dataExibicao.equals("18/02") || dataExibicao.equals("19/02")) {
            if(movieEntities != null) {
                movieEntities.addAll(movieRespository.findByscreeningDateTime("15 a 19/02", new Sort(Sort.Direction.ASC, "name")));
            } else {
                movieEntities = movieRespository.findByscreeningDateTime("15 a 19/02", new Sort(Sort.Direction.ASC, "name"));
            }
        }
        return movieEntities;
    }

    public MovieEntity getOne(long id) {
        return movieRespository.findOne(id);
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

    public MovieEntity newMovie(MovieDTO movieDTO) {
        MovieEntity movieEntity = MovieConverter.dtoToEntity(movieDTO);
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

    private Specifications createSpecifications(List<Specification> specs) {
        Specifications specifications = null;
        for (Specification spec : specs) {
            if (specifications == null) {
                specifications = Specifications.where(spec);
            } else {
                specifications.and(spec);
            }
        }

        return specifications;
    }

    private char getOperator(String value) {
        if (value.startsWith(">") || value.startsWith("<") || value.startsWith(":")) {
            return value.charAt(0);
        }
        return '=';
    }
}
