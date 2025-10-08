package com.swp.evmsystem.repository;

import com.swp.evmsystem.entity.EVModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EVModelRepository extends JpaRepository<EVModelEntity, Integer> {
    EVModelEntity save(EVModelEntity entity);
}
