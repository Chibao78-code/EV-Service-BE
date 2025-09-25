package com.swp.evmsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trung_tam")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCenterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "center_id")
    private int id;
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "address", length = 200, nullable = false)
    private String address;
    @Column(name = "phone", length = 10, nullable = false)
    private String phone;
    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;
    @OneToMany(mappedBy = "centerId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
}
