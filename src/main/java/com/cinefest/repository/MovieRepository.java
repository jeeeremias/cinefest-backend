package com.cinefest.repository;

import com.cinefest.entity.MovieEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MovieRepository extends PagingAndSortingRepository<MovieEntity, Integer> {
	List<MovieEntity> findByscreeningDateTime(String screeningDateTime, Sort sort);
}
