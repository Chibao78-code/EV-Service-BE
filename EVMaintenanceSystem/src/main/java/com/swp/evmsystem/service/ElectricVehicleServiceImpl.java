package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.response.ElectricVehicleDTO;
import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.entity.ElectricVehicleEntity;
import com.swp.evmsystem.enums.EvMaintenanceStatus;
import com.swp.evmsystem.mapper.ElectricVehicleMapper;
import com.swp.evmsystem.repository.ElectricVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElectricVehicleServiceImpl implements ElectricVehicleService {
    @Autowired
    ElectricVehicleRepository electricVehicleRepository;
    @Autowired
    ElectricVehicleMapper mapper;

    @Override
    public List<ElectricVehicleDTO> findByOwner(CustomerEntity owner) {
        return electricVehicleRepository.findByOwner(owner)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean create(ElectricVehicleEntity ev) {
        return electricVehicleRepository.save(ev) != null;
    }

    @Override
    public boolean isEvAvailableForBooking(Integer evId) {
        return electricVehicleRepository.findById(evId).get().getMaintenanceStatus() == EvMaintenanceStatus.AVAILABLE;
    }
}
