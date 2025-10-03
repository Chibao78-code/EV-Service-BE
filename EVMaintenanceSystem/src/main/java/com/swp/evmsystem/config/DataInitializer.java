package com.swp.evmsystem.config;

import com.swp.evmsystem.service.RoleService;
import com.swp.evmsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    if (roleService.getAllRoles()== 0)


    }
}
