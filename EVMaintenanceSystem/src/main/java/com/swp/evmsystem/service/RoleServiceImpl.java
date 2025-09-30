package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.RoleEntity;
import com.swp.evmsystem.enums.RoleName;
import com.swp.evmsystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleEntity findByName(String roleName) {
        try {
            RoleName roleEnum = RoleName.valueOf(roleName);
            return roleRepository.findByName(roleEnum);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public void createRole(String roleName) {
        try {
            RoleName roleEnum = RoleName.valueOf(roleName);
            if (findByName(roleName) == null) {
                RoleEntity role = new RoleEntity();
                role.setName(roleEnum);
                roleRepository.save(role);
                System.out.println("Created role: " + roleName);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role name: " + roleName);
        }
    }
}
