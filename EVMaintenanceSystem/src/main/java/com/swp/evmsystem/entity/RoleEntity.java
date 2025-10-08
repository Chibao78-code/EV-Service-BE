package com.swp.evmsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swp.evmsystem.enums.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 50, unique = true, nullable = false)
    RoleName name;

    @Override
    public String toString() {
        return name.toString();
    }
}
