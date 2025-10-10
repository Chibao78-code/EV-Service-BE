package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.request.BookingRequestDTO;
import com.swp.evmsystem.dto.response.BookingResponseDTO;
import com.swp.evmsystem.security.UserEntityDetails;
import org.springframework.stereotype.Service;


@Service
public interface BookingService {
    BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO);

    boolean isEVBelongToCustomer(BookingRequestDTO request, UserEntityDetails user);

    boolean isTimeBookingAvailable(BookingRequestDTO request);
}
