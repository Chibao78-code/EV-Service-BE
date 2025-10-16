package com.swp.evmsystem.entity;

import com.swp.evmsystem.enums.Role;
import com.swp.evmsystem.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Entity
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "nguoi_dung")
public abstract class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    int id;
    @Column(name = "email", length = 100, unique = true, nullable = true)
    String email;
    @Column(name = "phone", length = 10, unique = true, nullable = false)
    String phone;
    @Size(min = 8)
    @Column(name = "password", length = 100, nullable = false)
    String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    Role role;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    UserStatus status;
}