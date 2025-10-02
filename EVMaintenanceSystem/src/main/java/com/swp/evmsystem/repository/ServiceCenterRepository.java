package com.swp.evmsystem.repository;

import com.swp.evmsystem.entity.ServiceCenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceCenterRepository extends JpaRepository<ServiceCenterEntity, Integer> {
    ServiceCenterEntity findById(int id);
}
