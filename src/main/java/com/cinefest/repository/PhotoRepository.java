package com.cinefest.repository;

import com.cinefest.entity.PhotoEntity;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<PhotoEntity, Integer> {
}
