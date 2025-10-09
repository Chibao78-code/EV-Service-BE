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
    public String CustomerOnly() {
        return "Hello CUSTOMER! You can access this endpoint.";
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
