package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.request.BookingRequest;
import com.swp.evmsystem.entity.BookingEntity;
import com.swp.evmsystem.entity.ElectricVehicleEntity;
import com.swp.evmsystem.enums.BookingStatus;
import com.swp.evmsystem.repository.BookingRepository;
import com.swp.evmsystem.repository.ElectricVehicleRepository;
import com.swp.evmsystem.repository.ServiceCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ElectricVehicleRepository vehicleService;
    @Autowired
    private ServiceCenterRepository serviceCenterRepository;
    @Override
    public boolean createBooking(BookingEntity bookingEntity) {
        return bookingRepository.save(bookingEntity) != null;
    }


    @Override
    public boolean createBooking(BookingRequest request) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setBookingDate(request.getBookingDate());
        bookingEntity.setCenter(serviceCenterRepository.findById(request.getCenterId()).orElse(null));
        bookingEntity.setVehicle(vehicleService.findById(request.getVehicleId()).orElse(null));
        bookingEntity.setStatus(BookingStatus.PENDING);
        bookingEntity.setTimeSlot(request.getTimeSlot());
        return bookingRepository.save(bookingEntity) != null;
    }
}
