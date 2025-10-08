package com.swp.evmsystem.controller;

import com.swp.evmsystem.dto.response.ElectricVehicleDTO;
import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.security.UserEntityDetails;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
public class TestController {
    private final com.swp.evmsystem.service.ElectricVehicleService electricVehicleService;
    public TestController(com.swp.evmsystem.service.ElectricVehicleService electricVehicleService) {
        this.electricVehicleService = electricVehicleService;
    }
    public static Logger logger = org.slf4j.LoggerFactory.getLogger(TestController.class);
    // Bất kỳ user nào đã đăng nhập đều truy cập được
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello, this endpoint is PUBLIC (no role check)";
    }

    // Chỉ user có role CUSTOMER mới truy cập
    @GetMapping("/api/customer/vehicles")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getMyVehicles() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                logger.error("Unauthorized access attempt to vehicles endpoint");
                return ResponseEntity
                        .status(401)
                        .body("User not authenticated");
            }

            UserEntityDetails userDetails = (UserEntityDetails) authentication.getPrincipal();
            if (!(userDetails.getUser() instanceof CustomerEntity)) {
                logger.error("Non-customer user {} attempted to access vehicles", userDetails.getId());
                throw new AccessDeniedException("Only customers can access vehicles");
            }

            CustomerEntity customer = (CustomerEntity) userDetails.getUser();
            List<ElectricVehicleDTO> vehicles = null;

            logger.debug("Retrieved {} vehicles for customer {}", vehicles.size(), customer.getId());
            return ResponseEntity.ok(vehicles);
        } catch (ClassCastException e) {
            logger.error("Invalid authentication principal type: {}", e.getMessage());
            return ResponseEntity
                    .status(401)
                    .body("Invalid authentication");
        } catch (Exception e) {
            logger.error("Error retrieving vehicles: {}", e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body("Failed to retrieve vehicles");
        }
    }

    // Chỉ user có role ADMIN mới truy cập
    @GetMapping("/api/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminOnly() {
        return "Hello ADMIN! You can access this endpoint.";
    }

    // Cả ADMIN và CUSTOMER đều truy cập được
    @GetMapping("/api/both")
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public String bothAdminAndCustomer() {
        return "Hello USER! (ADMIN or CUSTOMER)";
    }
}
