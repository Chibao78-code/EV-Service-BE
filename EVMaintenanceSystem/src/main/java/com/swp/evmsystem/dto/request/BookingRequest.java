package com.swp.evmsystem.dto.request;

import com.swp.evmsystem.enums.TimeSlot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {
    Integer vehicleId;
    Integer centerId;
    LocalDate bookingDate;
    TimeSlot timeSlot;
}
