package com.swp.evmsystem.controller;

import com.swp.evmsystem.dto.response.ElectricVehicleDTO;
import com.swp.evmsystem.security.UserEntityDetails;
import com.swp.evmsystem.service.AuthService;
import com.swp.evmsystem.service.ElectricVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ElectricVehicleController {
    @Autowired
    private ElectricVehicleService electricVehicleService;
    @Autowired
    AuthService authService;

    @GetMapping("/me/vehicles")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getMyVehicles(@AuthenticationPrincipal UserEntityDetails userDetails) {
        List<ElectricVehicleDTO> vehicles = electricVehicleService.findByOwner(userDetails);
        return ResponseEntity.ok(vehicles);
    }
}
