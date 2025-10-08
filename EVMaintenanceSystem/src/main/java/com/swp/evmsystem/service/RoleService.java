package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.RoleEntity;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    boolean createRole(RoleEntity role);
    RoleEntity getRoleById(Integer roleId);
    Integer getAllCount();
    RoleEntity getRoleByName(String name);
}
