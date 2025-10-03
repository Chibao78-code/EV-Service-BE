package com.swp.evmsystem.dto.response;

import com.swp.evmsystem.enums.TimeSlot;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvaibleTimeSlot {
    LocalDate date;
    TimeSlot timeSlot;
    boolean available;

}
