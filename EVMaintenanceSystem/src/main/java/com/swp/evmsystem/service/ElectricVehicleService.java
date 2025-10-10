package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.response.ElectricVehicleDTO;
import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.entity.ElectricVehicleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ElectricVehicleService {
    List<ElectricVehicleDTO> findByOwner(CustomerEntity owner);

    boolean create(ElectricVehicleEntity ev);

    boolean isEvAvailableForBooking(Integer evId);
}
