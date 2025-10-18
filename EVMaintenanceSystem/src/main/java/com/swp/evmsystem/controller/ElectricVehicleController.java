java
        package com.swp.evmsystem.controller;

        import com.swp.evmsystem.dto.response.ElectricVehicleDTO;
        import com.swp.evmsystem.entity.CustomerEntity;
        import com.swp.evmsystem.security.UserEntityDetails;
        import com.swp.evmsystem.service.ElectricVehicleService;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.access.AccessDeniedException;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.security.core.annotation.AuthenticationPrincipal;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.List;

        @RestController
        @RequestMapping("/api")
        public class ElectricVehicleController {

            private final ElectricVehicleService electricVehicleService;

            public ElectricVehicleController(ElectricVehicleService electricVehicleService) {
                this.electricVehicleService = electricVehicleService;
            }

            @GetMapping("/me/vehicles")
            @PreAuthorize("hasRole('CUSTOMER')")
            public ResponseEntity<?> getMyVehicles(@AuthenticationPrincipal UserEntityDetails userDetails) {
                try {
                    // Kiểm tra nếu người dùng chưa đăng nhập
                    if (userDetails == null) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication required");
                    }
                    
            }
        }