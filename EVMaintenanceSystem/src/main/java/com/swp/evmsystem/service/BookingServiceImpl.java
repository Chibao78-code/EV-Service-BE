package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.request.BookingRequestDTO;
import com.swp.evmsystem.entity.BookingEntity;
import com.swp.evmsystem.enums.BookingStatus;
import com.swp.evmsystem.enums.TimeSlot;
import com.swp.evmsystem.repository.BookingRepository;
import com.swp.evmsystem.repository.ElectricVehicleRepository;
import com.swp.evmsystem.repository.ServiceCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ElectricVehicleRepository vehicleService;
    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    @Override
    public boolean createBooking(Integer id , BookingRequestDTO request) { // id is vehicle id
        BookingEntity bookingEntity = new BookingEntity(); // Create new booking entity
        bookingEntity.setBookingDate(request.getBookingDate()); // Set booking date
        //bookingEntity.setCenter(serviceCenterRepository.findById(request.getCenterId()).orElse(null));
        bookingEntity.setVehicle(vehicleService.findById(id).orElse(null)); // Set vehicle
        bookingEntity.setStatus(BookingStatus.PENDING); // Set status to PENDING
        bookingEntity.setTimeSlot(request.getTimeSlot()); // Set time slot
        return bookingRepository.save(bookingEntity) != null; // Save booking and return true if successful
    }

    // Build booking entity
    BookingEntity bookingEntity = new BookingEntity(); // Create new booking entity
        bookingEntity.setBookingDate(request.getBookingDate()); // Set booking date
        bookingEntity.setVehicle(vehicleOpt.get()); // safe because of the isEmpty check above
        bookingEntity.setStatus(BookingStatus.PENDING); // Set status to PENDING
        bookingEntity.setTimeSlot(request.getTimeSlot()); // Set time slot

    // If a center id was provided, set the center when it exists
        if (request.getCenterId() != null) { // If center id is provided
        serviceCenterRepository.findById(request.getCenterId()) // Find center by id
                .ifPresent(bookingEntity::setCenter); // Set center if found
    }

    // Persist booking; return true when saved (non-null)
        return bookingRepository.save(bookingEntity) != null;
}

    @Override
    public BookingEntity getBookingById(Integer bookingId) { // Get booking by id
        return bookingRepository.findById(bookingId).orElse(null); // Return booking or null if not found
    }

    @Override
    public int getBookingsByDateAndTimeSlot(LocalDate bookingDate, TimeSlot timeSlot) { // Get bookings by date and time slot
        return bookingRepository.countAllByBookingDateAndTimeSlot( bookingDate,  timeSlot);
    }

    @Override
    public int countBookingsByCenterAndDate(int centerId, LocalDate bookingDate) {
        return bookingRepository.countBookingEntityByCenterIdAndBookingDate(centerId, bookingDate);
    }
}
