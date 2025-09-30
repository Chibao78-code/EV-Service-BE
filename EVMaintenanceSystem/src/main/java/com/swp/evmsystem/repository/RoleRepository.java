package com.swp.evmsystem.repository;

import com.swp.evmsystem.entity.RoleEntity;
import com.swp.evmsystem.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    RoleEntity findByName(RoleName name);
}
