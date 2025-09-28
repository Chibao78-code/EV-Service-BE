package com.swp.evmsystem.repository;

import com.swp.evmsystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByPhone(String phone);
    Optional<UserEntity> findByEmail(String email);
    // Tìm user theo username
    Optional<UserEntity> findByUsername(String username);

    // Kiểm tra user có tồn tại theo số điện thoại hay không
    boolean existsByPhone(String phone);

    // Kiểm tra user có tồn tại theo email hay không
    boolean existsByEmail(String email);

    // Tìm danh sách user theo role (VD: "ADMIN", "CUSTOMER")
    List<UserEntity> findByRole(String role);

    // Tìm danh sách user theo trạng thái active (true = đang hoạt động, false = bị khóa)
    List<UserEntity> findByActive(boolean active);

}
