package com.swp.evmsystem.config;

import com.swp.evmsystem.service.RoleService;
import com.swp.evmsystem.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void init() {
        // Create roles if they don't exist
        createRoleIfNotExists("ADMIN");
        createRoleIfNotExists("STAFF");
        createRoleIfNotExists("TECHNICIAN");
        createRoleIfNotExists("CUSTOMER");

        // Create default admin user
        userService.createUser("admin", "123456", "ADMIN");

        System.out.println("✅ Data initialization completed");
    }

    private void createRoleIfNotExists(String roleName) {
        if (roleService.findByName(roleName) == null) {
            roleService.createRole(roleName);
        }
    }
}