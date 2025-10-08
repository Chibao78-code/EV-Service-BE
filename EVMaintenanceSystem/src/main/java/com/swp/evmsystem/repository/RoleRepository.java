package com.swp.evmsystem.repository;

import com.swp.evmsystem.entity.RoleEntity;
import com.swp.evmsystem.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity save(RoleEntity role);

    Optional<RoleEntity> findById(Integer id);

    List<RoleEntity> findAll();

    RoleEntity findByName(RoleName name);
}
