package com.swp.evmsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nguoi_dung")
public class UserEntity {
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
    @OneToOne
    @JoinColumn(name = "customer_id", unique = true)
    private CustomerEntity customer;
    @OneToOne
    @JoinColumn(name = "employee_id", unique = true)
    private EmployeeEntity employee;

    public enum UserStatus {
        ACTIVE,
        INACTIVE
    }
}