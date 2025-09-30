package com.swp.evmsystem.controller;

import com.swp.evmsystem.dto.response.UserInfo;
import com.swp.evmsystem.entity.UserEntity;
import com.swp.evmsystem.security.UserEntityDetails;
import com.swp.evmsystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "APIs for user management operations")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    @Operation(summary = "Get current user profile", description = "Lấy thông tin profile của user hiện tại")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lấy thông tin thành công",
                    content = @Content(schema = @Schema(implementation = UserInfo.class))),
            @ApiResponse(responseCode = "401", description = "Chưa đăng nhập")
    })
    public ResponseEntity<UserInfo> getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntityDetails userDetails = (UserEntityDetails) authentication.getPrincipal();
        UserEntity user = userDetails.getUser();

        UserInfo userInfo = new UserInfo(user);
        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/check-customer/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Check if user is customer", description = "Kiểm tra xem user có phải là khách hàng không")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Kiểm tra thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập")
    })
    public ResponseEntity<Boolean> checkIfCustomer(@PathVariable Integer userId) {
        boolean isCustomer = userService.isCustomer(userId);
        return ResponseEntity.ok(isCustomer);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create new user", description = "Tạo user mới (chỉ admin)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tạo user thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
            @ApiResponse(responseCode = "400", description = "Dữ liệu không hợp lệ")
    })
    public ResponseEntity<String> createUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String roleName) {
        try {
            userService.createUser(username, password, roleName);
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating user: " + e.getMessage());
        }
    }

    @GetMapping("/admin-only")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Admin only endpoint", description = "Endpoint chỉ dành cho admin")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("This is admin-only content");
    }

    @GetMapping("/staff-only")
    @PreAuthorize("hasRole('STAFF')")
    @Operation(summary = "Staff only endpoint", description = "Endpoint chỉ dành cho nhân viên")
    public ResponseEntity<String> staffOnly() {
        return ResponseEntity.ok("This is staff-only content");
    }

    @GetMapping("/technician-only")
    @PreAuthorize("hasRole('TECHNICIAN')")
    @Operation(summary = "Technician only endpoint", description = "Endpoint chỉ dành cho kỹ thuật viên")
    public ResponseEntity<String> technicianOnly() {
        return ResponseEntity.ok("This is technician-only content");
    }

    @GetMapping("/customer-only")
    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(summary = "Customer only endpoint", description = "Endpoint chỉ dành cho khách hàng")
    public ResponseEntity<String> customerOnly() {
        return ResponseEntity.ok("This is customer-only content");
    }
}
