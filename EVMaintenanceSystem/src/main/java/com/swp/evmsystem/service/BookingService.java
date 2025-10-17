package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.request.BookingRequestDTO;
import com.swp.evmsystem.dto.response.BookingResponseDTO;
import com.swp.evmsystem.entity.ElectricVehicleEntity;
import com.swp.evmsystem.security.UserEntityDetails;
import org.springframework.stereotype.Service;


@Service
public interface BookingService {
    BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO, UserEntityDetails user);

    ElectricVehicleEntity findElectricVehicleById(Integer evId);

    void validateOwnership(ElectricVehicleEntity ev, UserEntityDetails userDetails);

    void validateTimeBookingAvailable(BookingRequestDTO request);

}
