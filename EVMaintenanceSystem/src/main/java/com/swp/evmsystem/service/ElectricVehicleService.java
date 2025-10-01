package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.entity.ElectricVehicleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ElectricVehicleService {
    List<ElectricVehicleEntity> findByOwner(CustomerEntity owner);
}
