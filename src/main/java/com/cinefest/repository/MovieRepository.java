package com.cinefest.repository;

import com.cinefest.entity.MovieEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Long>, JpaSpecificationExecutor<MovieEntity> {
  List<MovieEntity> findByscreeningDateTime(String screeningDateTime, Sort sort);
}
