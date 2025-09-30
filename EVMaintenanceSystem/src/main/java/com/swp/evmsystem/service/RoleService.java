package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.RoleEntity;

public interface RoleService {
    RoleEntity findByName(String roleName);
    void createRole(String roleName);
}
