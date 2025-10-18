package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.RoleEntity;
import com.swp.evmsystem.enums.RoleName;
import com.swp.evmsystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public boolean createRole(RoleEntity role) {
        return roleRepository.save(role) != null;
    }

    @Override
    public RoleEntity getRoleById(Integer roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public Integer getAllCount() {
        return roleRepository.findAll().size();
    }


    @Override
    public RoleEntity getRoleByName(String name) {
        try {
            RoleName roleName = RoleName.valueOf(name); // Convert String to RoleName enum
            return roleRepository.findByName(roleName); // Fetch RoleEntity by RoleName
        } catch (IllegalArgumentException | NullPointerException ex) {
            return null; // Return null if the name is invalid or null
        }
}
