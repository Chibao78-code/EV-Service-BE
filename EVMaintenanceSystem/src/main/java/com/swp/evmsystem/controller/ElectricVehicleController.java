package com.swp.evmsystem.controller;

import com.swp.evmsystem.dto.response.ElectricVehicleDto;
import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.security.UserEntityDetails;
import com.swp.evmsystem.service.ElectricVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/me")
public class ElectricVehicleController {
    @Autowired
    private ElectricVehicleService electricVehicleService;

    @GetMapping("/vehicles")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getMyVehicles(@AuthenticationPrincipal UserEntityDetails user) {
        if (user == null || !(user.getUser() instanceof CustomerEntity)) {
            return ResponseEntity.status(401).body("Unauthorized access");
        }

        List<ElectricVehicleDto> vehicles = electricVehicleService
                .findByOwner((CustomerEntity) user.getUser())
                .stream()
                .map(ElectricVehicleDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(vehicles);
    }
}
