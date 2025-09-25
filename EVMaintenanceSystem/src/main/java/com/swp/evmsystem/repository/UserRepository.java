package com.swp.evmsystem.repository;

import com.swp.evmsystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByPhone(String phone);
    Optional<UserEntity> findByEmail(String email);
}
