package com.swp.evmsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "xe_dien")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ElectricVehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    int id;
    @Column(name = "model", length = 100, nullable = false)
    String model;
    @Column(name = "VIN", length = 17, unique = true, nullable = false)
    String VIN;
    @Column(name = "license_plate",length = 100, nullable = false)
    String licensePlate;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    CustomerEntity owner;
}
