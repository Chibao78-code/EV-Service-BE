package com.swp.evmsystem.config;

import com.swp.evmsystem.entity.RoleEntity;
import com.swp.evmsystem.enums.RoleName;
import com.swp.evmsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer  implements CommandLineRunner {
    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        if (roleService.getAllCount() > 0) {
            return;
        }

        RoleEntity role = new RoleEntity();
        role.setName(RoleName.valueOf("ADMIN"));
        roleService.createRole(role);
        role.setName(RoleName.valueOf("STAFF"));
        roleService.createRole(role);
        role.setName(RoleName.valueOf("TECHNICIAN"));
        roleService.createRole(role);
        role.setName(RoleName.valueOf("CUSTOMER"));
        roleService.createRole(role);
    }
}
