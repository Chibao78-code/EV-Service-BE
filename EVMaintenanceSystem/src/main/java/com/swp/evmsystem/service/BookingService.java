package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.request.BookingRequest;
import com.swp.evmsystem.entity.BookingEntity;
import com.swp.evmsystem.enums.TimeSlot;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface BookingService {
    boolean createBooking(Integer vehicleId ,BookingRequest request);
    BookingEntity getBookingById(Integer bookingId);
    int getBookingsByDateAndTimeSlot(LocalDate bookingDate, TimeSlot timeSlot);
}
