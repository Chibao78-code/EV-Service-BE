package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.request.BookingRequest;
import com.swp.evmsystem.entity.BookingEntity;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {
    boolean createBooking(BookingEntity bookingEntity);
    boolean createBooking(BookingRequest request);
}
