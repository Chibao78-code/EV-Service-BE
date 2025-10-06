package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.request.BookingRequest;
import com.swp.evmsystem.entity.ServiceCenterEntity;
import com.swp.evmsystem.entity.TimeSlotAvailability;
import com.swp.evmsystem.enums.TimeSlot;
import com.swp.evmsystem.repository.ServiceCenterRepository;
import com.swp.evmsystem.repository.TimeSlotAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeSlotAvailabilityServiceImpl implements TimeSlotAvailabilityService {
    @Autowired
    private TimeSlotAvailabilityRepository timeSlotAvailabilityRepository;
    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    @Override
    public List<TimeSlotAvailability> getTimeSlotByDate(LocalDate date) {
        return timeSlotAvailabilityRepository.findAllByDate(date);
    }

    @Override
    public boolean addTimeSlotAvailabilityService(TimeSlotAvailability timeSlotAvailability) {
        return timeSlotAvailabilityRepository.save(timeSlotAvailability) != null;
    }

    @Override
    public boolean isTimeAvailable(BookingRequest request) {
        LocalDate date = request.getBookingDate();
        TimeSlot timeSlot = request.getTimeSlot();
        ServiceCenterEntity serviceCenter = serviceCenterRepository.findById(request.getCenterId()).orElse(null);

        TimeSlotAvailability timeSlotAvailability = timeSlotAvailabilityRepository.findTimeSlotAvailabilityByDateAndTimeSlotAndServiceCenter(date, timeSlot, serviceCenter);
        if (timeSlotAvailability.isAvailable()) {
            timeSlotAvailability.setBookingNumbers(timeSlotAvailability.getBookingNumbers() + 1);
            timeSlotAvailability.setAvailable();
            timeSlotAvailabilityRepository.save(timeSlotAvailability);
            return true;
        } else {
            return false;
        }
    }

}
