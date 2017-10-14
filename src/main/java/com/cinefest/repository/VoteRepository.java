package com.cinefest.repository;

import com.cinefest.review.ReviewEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VoteRepository extends PagingAndSortingRepository<ReviewEntity, Integer> {
  Long countByDateTime(String dateTime);
}
