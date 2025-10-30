package com.swp.evmsystem.entity;

import com.swp.evmsystem.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "lich_hen",
        uniqueConstraints = {@UniqueConstraint(
                name = "uk_vehicle_date_time",
                columnNames = {"vehicle_id", "booking_date", "booking_time"}
        )
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    int bookingId;
    @Column(name = "customer_name", length = 100, nullable = false)
    String customerName;
    @Column(name = "customer_phone", length = 13, nullable = false)
    String customerPhone;
    @Column(name = "customer_email", length = 100, nullable = false)
    String customerEmail;
    @Column(name = "customer_address", length = 200)
    String customerAddress;
    @Column(name = "customer_note", length = 500)
    String customerNote;
    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
    ElectricVehicleEntity vehicle;
    @Column(name = "booking_code", length = 20, nullable = false, unique = true)
    String bookingCode;
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
