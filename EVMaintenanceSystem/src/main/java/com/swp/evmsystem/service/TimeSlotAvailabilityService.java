package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.TimeSlotAvailability;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface TimeSlotAvailabilityService {
    List<TimeSlotAvailability> getTimeSlotByDate(LocalDate date);

    boolean addTimeSlotAvailabilityService(TimeSlotAvailability timeSlotAvailability);
}
