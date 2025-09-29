package com.swp.evmsystem.entity;

import com.swp.evmsystem.enums.BookingStatus;
import com.swp.evmsystem.enums.TimeSlot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
    @Enumerated(EnumType.STRING)
    LocalDate bookingDate;
    @Column(name = "time_slot", length = 20, nullable = false)
    TimeSlot timeSlot;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    BookingStatus status;
}
