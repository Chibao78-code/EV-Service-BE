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
@Table(name = "xe_dien")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ElectricVehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model")
    EVModelEntity model;
    @Column(name = "VIN", length = 17, unique = true, nullable = false)
    String vIN;
    @Column(name = "license_plate", length = 10, nullable = false)
    String licensePlate;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    CustomerEntity owner;
    @OneToMany(mappedBy = "vehicle")
    List<BookingEntity> bookings = new ArrayList<BookingEntity>();
}
