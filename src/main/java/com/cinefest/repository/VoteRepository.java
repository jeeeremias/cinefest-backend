package com.cinefest.repository;

import com.cinefest.entity.VoteEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VoteRepository extends PagingAndSortingRepository<VoteEntity, Integer> {
	Long countByDay(String day);
}
