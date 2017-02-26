package com.cinefest.repository;

import com.cinefest.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, String> {
}
