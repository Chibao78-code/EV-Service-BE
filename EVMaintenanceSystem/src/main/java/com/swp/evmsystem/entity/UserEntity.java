package com.swp.evmsystem.entity;

import com.swp.evmsystem.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "nguoi_dung")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    int id;
    @Column(name = "email", length = 100, unique = true, nullable = true)
    String email;
    @Column(name = "phone", length = 10, unique = true, nullable = false)
    String phone;
    @Column(name = "full_name", length = 100, nullable = false)
    String fullName;
    @Column(name = "password", length = 100, nullable = false)
    String password;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    RoleEntity role;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    UserStatus status;
}