package com.swp.evmsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trung_tam")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ServiceCenterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "center_id")
    int id;
    @Column(name = "name", length = 100, nullable = false)
    String name;
    @Column(name = "address", length = 200, nullable = false)
    String address;
    @Column(name = "phone", length = 10, nullable = false)
    String phone;
    @Column(name = "email", length = 100, unique = true, nullable = false)
    String email;
    @OneToMany(mappedBy = "centerId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
}
