package com.swp.evmsystem.repository;

import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.entity.ElectricVehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectricVehicleRepository extends JpaRepository<ElectricVehicleEntity, Integer> {
    List<ElectricVehicleEntity> findByOwner(CustomerEntity owner);
}
