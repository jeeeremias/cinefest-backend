package com.cinefest.rest.service;

import com.cinefest.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinefest.repository.MovieRepository;
import com.cinefest.repository.VoteRepository;
import com.cinefest.entity.VoteEntity;

@Service
@Transactional
public class VoteRestService {

	@Autowired
	VoteRepository voteRespository;
	
	@Autowired
    MovieRepository movieRespository;

	public VoteEntity save(VoteEntity voteEntity) {
		MovieEntity movieEntity = movieRespository.findOne(voteEntity.getMovieId());
		voteEntity.setMovie(movieEntity);
		return voteRespository.save(voteEntity);
	}
	
	public Iterable<VoteEntity> getAll(int offset, int size) {
		return voteRespository.findAll(new Sort(Sort.Direction.ASC, "day"));
	}
	
	public Long countByDia(String day) {
		return voteRespository.countByDateTime(day);
	}
}
