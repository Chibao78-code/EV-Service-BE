package com.swp.evmsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "nguoi_dung")
public abstract class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "email", length = 100, unique = true, nullable = true)
    private String email;
    @Column(name = "phone", length = 10, unique = true, nullable = false)
    private String phone;
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status;

    public enum UserStatus {
        ACTIVE,
        INACTIVE
    }
}