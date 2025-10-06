package com.swp.evmsystem.controller;

import com.swp.evmsystem.dto.response.ElectricVehicleDto;
import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.security.UserEntityDetails;
import com.swp.evmsystem.service.ElectricVehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ElectricVehicleController {
    private final ElectricVehicleService electricVehicleService;

    public ElectricVehicleController(ElectricVehicleService electricVehicleService) {
        this.electricVehicleService = electricVehicleService;
    }


    @Operation(summary = "Get user's vehicles", description = "Retrieves all vehicles owned by the authenticated customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicles retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "User is not a customer"),
            @ApiResponse(responseCode = "401", description = "User is not authenticated")
    })
    @GetMapping("/me/vehicles")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getMyVehicles() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return ResponseEntity
                        .status(401)
                        .body("User not authenticated");
            }

            UserEntityDetails userDetails = (UserEntityDetails) authentication.getPrincipal();
            if (!(userDetails.getUser() instanceof CustomerEntity)) {

                throw new AccessDeniedException("Only customers can access vehicles");
            }

            CustomerEntity customer = (CustomerEntity) userDetails.getUser();
            List<ElectricVehicleDto> vehicles = electricVehicleService
                    .findByOwner(customer)
                    .stream()
                    .map(ElectricVehicleDto::new)
                    .toList();


            return ResponseEntity.ok(vehicles);
        } catch (ClassCastException e) {

            return ResponseEntity
                    .status(401)
                    .body("Invalid authentication");
        } catch (Exception e) {

            return ResponseEntity
                    .internalServerError()
                    .body("Failed to retrieve vehicles");
        }
    }
}
