package com.swp.evmsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 50, unique = true, nullable = false)
    private RoleName name;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserEntity> users = new ArrayList<UserEntity>();

    public enum RoleName {
        ADMIN,
        STAFF,
        TECHNICIAN,
        CUSTOMER
    }
}
