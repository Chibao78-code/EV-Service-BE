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
    } // Create a new role and return true if successful

    @Override // Retrieve a role by its ID
    public RoleEntity getRoleById(Integer roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override // Get the total count of all roles
    public Integer getAllCount() {
        return roleRepository.findAll().size();
    }


    @Override // Retrieve a role by its name
    public RoleEntity getRoleByName(String name) {
        try { // Try to convert the name to RoleName enum and fetch the role
            RoleName roleName = RoleName.valueOf(name); // Convert String to RoleName enum
            return roleRepository.findByName(roleName); // Fetch RoleEntity by RoleName
        } catch (IllegalArgumentException | NullPointerException ex) {
            return null; // Return null if the name is invalid or null
        }
}
