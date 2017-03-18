package com.cinefest.service;

import com.cinefest.entity.MovieEntity;
import com.cinefest.pojo.dto.MovieDTO;
import com.cinefest.pojo.params.QueryParams;
import com.cinefest.repository.MovieRepository;
import com.cinefest.repository.VoteRepository;
import com.cinefest.rest.util.converter.GenericQueryParamsConverter;
import com.cinefest.util.converter.MovieConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRespository;

    @Autowired
    VoteRepository voteRespository;

    @Autowired
    protected GenericQueryParamsConverter queryParamsConverter;

    public List<MovieEntity> getAll(QueryParams queryParams) {
        PageRequest pageRequest = createPageRequest(queryParams.getPage(), queryParams.getSize(), queryParams.getSort());
        return movieRespository.findAll(pageRequest).getContent();
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

    public MovieEntity getOne(Integer id) {
        return movieRespository.findOne(id);
    }

    public boolean exist(Integer id) {
        return movieRespository.exists(id);
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


    private PageRequest createPageRequest(int offset, int size, List<String> sortParams) {
        Sort.Direction direction;
        List<Sort.Order> orders = new ArrayList<>();
        String prop;
        for (String param : sortParams) {
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
        PageRequest pageRequest = new PageRequest(offset, size, sort);
        return pageRequest;
    }
}
