package com.swp.evmsystem.dto.response;

import com.swp.evmsystem.entity.TimeSlotAvailability;
import com.swp.evmsystem.enums.TimeSlot;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class AvaibleTimeSlotDTO {
    LocalDate date;
    TimeSlot timeSlot;
    boolean available;

    public AvaibleTimeSlotDTO(TimeSlotAvailability timeSlotAvailability) {
    }
}
