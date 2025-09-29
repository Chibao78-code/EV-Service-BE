package com.swp.evmsystem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // Bất kỳ user nào đã đăng nhập đều truy cập được
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello, this endpoint is PUBLIC (no role check)";
    }

    // Chỉ user có role CUSTOMER mới truy cập
    @GetMapping("/api/customer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String customerOnly() {
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
