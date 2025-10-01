package com.swp.evmsystem.repository;

import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.entity.UserEntity;
import com.swp.evmsystem.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByPhone(String phone);
    Optional<UserEntity> findByEmail(String email);
    RoleName getRoleNameById(Integer id);

}
