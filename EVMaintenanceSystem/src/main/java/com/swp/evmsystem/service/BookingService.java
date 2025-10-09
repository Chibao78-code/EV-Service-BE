package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.request.BookingRequestDTO;
import com.swp.evmsystem.entity.BookingEntity;
import com.swp.evmsystem.enums.TimeSlot;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface BookingService {
    boolean createBooking(Integer vehicleId , BookingRequestDTO request);
    BookingEntity getBookingById(Integer bookingId);
    int getBookingsByDateAndTimeSlot(LocalDate bookingDate, TimeSlot timeSlot);
    int countBookingsByCenterAndDate(int centerId, LocalDate bookingDate);
}
