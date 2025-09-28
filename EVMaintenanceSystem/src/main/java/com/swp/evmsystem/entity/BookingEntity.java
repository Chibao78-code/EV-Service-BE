package com.swp.evmsystem.entity;

import com.swp.evmsystem.enums.TimeSlot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "lich_hen")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    int id;
    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
    ElectricVehicleEntity vehicle;
    @ManyToOne
    @JoinColumn(name = "center_id", referencedColumnName = "center_id")
    ServiceCenterEntity center;
    @Column(name = "booking_date", nullable = false)
    LocalDate bookingDate;
    @Column(name = "time_slot", length = 20, nullable = false)
    TimeSlot timeSlot;
    @Column(name = "status", length = 20, nullable = false)
    String status;
}
