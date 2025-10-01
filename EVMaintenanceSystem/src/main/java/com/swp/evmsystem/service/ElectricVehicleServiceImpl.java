package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.entity.ElectricVehicleEntity;
import com.swp.evmsystem.repository.ElectricVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectricVehicleServiceImpl implements ElectricVehicleService {
    @Autowired
    ElectricVehicleRepository electricVehicleRepository;

    @Override
    public List<ElectricVehicleEntity> findByOwner(CustomerEntity owner) {
        return electricVehicleRepository.findByOwner(owner);
    }
}
