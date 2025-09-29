package com.swp.evmsystem.config;

import com.swp.evmsystem.service.RoleService;
import com.swp.evmsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    //Define some initial data for testing
//    @Autowired
//    private UserService userService;
//    Khong biet
//    @Autowired
//    private RoleService roleService;

    @Autowired
    public DataInitializer(UserService userService, RoleService roleService) {
        // Initialize roles
//        roleService.createRole("ADMIN");
//        roleService.createRole("USER");
//        roleService.createRole("MANAGER");    java
package com.swp.evmsystem.config;

import com.swp.evmsystem.service.RoleService;
import com.swp.evmsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void init() {
        // Create 4 roles
        roleService.createRole("ADMIN");
        roleService.createRole("USER");
        roleService.createRole("MANAGER");
        roleService.createRole("TECHNICIAN");

        // Create example users
        userService.createUser("admin", "admin123", "ADMIN");
        userService.createUser("manager", "manager123", "MANAGER");
    }
}
}
