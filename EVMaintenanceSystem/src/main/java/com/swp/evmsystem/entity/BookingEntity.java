package com.swp.evmsystem.entity;

import com.swp.evmsystem.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
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
    int bookingId;
    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
    ElectricVehicleEntity vehicle;
    @ManyToOne
    @JoinColumn(name = "center_id", referencedColumnName = "center_id")
    ServiceCenterEntity center;
    @Column(name = "booking_date", nullable = false, columnDefinition = "DATE")
    LocalDate bookingDate;
    @Column(name = "booking_time", nullable = false, columnDefinition = "TIME")
    LocalTime bookingTime;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    BookingStatus status;
}
