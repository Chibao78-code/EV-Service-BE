package com.swp.evmsystem.entity;

import com.swp.evmsystem.enums.TimeSlot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "lich_kha_dung",
uniqueConstraints = @UniqueConstraint(columnNames = {"date", "time_slot", "center_id"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class TimeSlotAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    int id;
    @Column(name = "date")
    LocalDate date;
    @Enumerated(EnumType.STRING)
    @Column(name = "time_slot")
    TimeSlot timeSlot;
    @ManyToOne
    @JoinColumn(name = "center_id")
    ServiceCenterEntity serviceCenter;
    @Column(name =  "available_technician")
    int availableTechnician;
    @Column(name = "booking_numbers")
    int bookingNumbers;
    @Column(name = "available")
    boolean available;

    public void setAvailable() {
        this.available = bookingNumbers < availableTechnician;
    }
}
