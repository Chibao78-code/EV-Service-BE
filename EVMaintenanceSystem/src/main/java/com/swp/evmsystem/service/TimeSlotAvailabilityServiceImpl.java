package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.TimeSlotAvailability;
import com.swp.evmsystem.repository.TimeSlotAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TimeSlotAvailabilityServiceImpl implements TimeSlotAvailabilityService {
    @Autowired
    private TimeSlotAvailabilityRepository timeSlotAvailabilityRepository;


    @Override
    public List<TimeSlotAvailability> getTimeSlotByDate(LocalDate date) {
        return timeSlotAvailabilityRepository.findAllByDate(date);
    }

    @Override
    public boolean addTimeSlotAvailabilityService(TimeSlotAvailability timeSlotAvailability) {
        return timeSlotAvailabilityRepository.save(timeSlotAvailability) != null;
    }
}
