package com.swp.evmsystem.dto.response;

import com.swp.evmsystem.entity.TimeSlotAvailability;
import com.swp.evmsystem.enums.TimeSlot;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvaibleTimeSlotDto {
    LocalDate date;
    TimeSlot timeSlot;
    boolean available;

    public AvaibleTimeSlotDto(TimeSlotAvailability timeSlot) {
        this.date = timeSlot.getDate();
        this.timeSlot = timeSlot.getTimeSlot();
        this.available = timeSlot.isAvailable();
    }
}
