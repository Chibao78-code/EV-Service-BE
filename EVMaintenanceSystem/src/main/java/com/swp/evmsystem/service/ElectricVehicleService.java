package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.response.ElectricVehicleDTO;
import com.swp.evmsystem.entity.ElectricVehicleEntity;
import com.swp.evmsystem.security.UserEntityDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ElectricVehicleService {
    boolean create(ElectricVehicleEntity ev);

    boolean isEvAvailableForBooking(Integer evId);

    List<ElectricVehicleDTO> findByOwner(UserEntityDetails userDetails);
}
