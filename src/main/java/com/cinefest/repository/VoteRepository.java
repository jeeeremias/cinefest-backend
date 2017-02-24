package com.cinefest.repository;

import com.cinefest.entity.Vote;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VoteRepository extends PagingAndSortingRepository<Vote, Integer> {
	Long countByDay(String day);
}
