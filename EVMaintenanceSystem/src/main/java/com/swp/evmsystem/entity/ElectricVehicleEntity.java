package com.swp.evmsystem.entity;

import com.swp.evmsystem.enums.EvMaintenanceStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "xe_dien")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
public class ElectricVehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model")
    EvModelEntity model;
    @Column(name = "vin", length = 17, unique = true)
    String vin;
    @Column(name = "license_plate", length = 10, unique = true)
    String licensePlate;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    CustomerEntity owner;
    @Enumerated(EnumType.STRING)
    @Column(name = "maintenance_status", length = 20, nullable = false)
    private EvMaintenanceStatus maintenanceStatus = EvMaintenanceStatus.AVAILABLE;
}
